package com.mac.m2land_cms.service.impl;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.JsonObject;
import com.mac.m2land_cms.model.entity.Topic;
import com.mac.m2land_cms.repository.TopicRepository;
import com.mac.m2land_cms.service.inf.FirebaseMessagingService;
import com.mac.m2land_cms.service.inf.TopicSubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The class FirebaseMessagingServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">an</a>
 */
@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {

    private static final Logger log = LoggerFactory.getLogger(FirebaseMessagingServiceImpl.class);

    @Value("${firebase.serverKey}")
    private String serverKey;

    private final TopicRepository topicRepository;
    private final TopicSubscriptionService topicSubscriptionService;

    @Autowired
    public FirebaseMessagingServiceImpl(TopicRepository topicRepository, TopicSubscriptionService topicSubscriptionService) {
        this.topicRepository = topicRepository;
        this.topicSubscriptionService = topicSubscriptionService;
    }

    @Override
    public void sendNotificationToBatch(List<String> tokens, String title, String body) throws FirebaseMessagingException {
        Map<String, List<String>> batches = splitTokensIntoBatches(tokens);

        sendNotificationsInBatches(batches, title, body);
    }

    private Map<String, List<String>> splitTokensIntoBatches(List<String> tokens) {
        Map<String, List<String>> batches = new HashMap<>();

        for (String token : tokens) {
            List<Topic> topicsToken = topicRepository.findAllByUserDeviceToken(token);
            List<String> topics = topicsToken.stream()
                    .map(Topic::getTopicName)
                    .collect(Collectors.toList());
            for (String topic : topics) {
                batches.computeIfAbsent(topic, k -> new ArrayList<>()).add(token);
            }
        }

        return batches;
    }

    private void sendNotificationsInBatches(Map<String, List<String>> batches, String title, String body) throws FirebaseMessagingException {
        System.out.println("start sendNotificationsInBatches...");
        for (Map.Entry<String, List<String>> entry : batches.entrySet()) {
            String topic = entry.getKey();
            List<String> tokens = entry.getValue();

            topicSubscriptionService.subscribeToTopic(tokens, topic);

            if (!tokens.isEmpty()) {
                sendNotificationToTopic(tokens, title, body, topic);
            }
        }
    }

    private void sendNotificationToTopic(List<String> tokens, String title, String body, String topic) {
        System.out.println("sendNotificationToTopic...");
        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + serverKey);
            conn.setRequestProperty("Content-Type", "application/json");

            JsonObject payload = new JsonObject();
            payload.addProperty("title", title);
            payload.addProperty("body", body);

            JsonObject request = new JsonObject();
            request.add("notification", payload);
            request.addProperty("to", "/topics/" + topic);

            conn.setDoOutput(true);
            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(request.toString().getBytes());
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Successs...");
            } else {
                System.out.println("Error...");
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
