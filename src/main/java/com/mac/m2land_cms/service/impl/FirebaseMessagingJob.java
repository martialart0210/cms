package com.mac.m2land_cms.service.impl;


import com.google.firebase.messaging.FirebaseMessagingException;
import com.mac.m2land_cms.model.entity.PushNotification;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.repository.PushNotificationRepository;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.service.inf.FirebaseMessagingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * The class FirebaseMessagingJob.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Component
public class FirebaseMessagingJob {

    private final FirebaseMessagingService firebaseMessagingService;

    private final PushNotificationRepository pushNotificationService;

    private final UserRepository userRepository;

    public FirebaseMessagingJob(FirebaseMessagingService firebaseMessagingService, PushNotificationRepository pushNotificationService, UserRepository userRepository) {
        this.firebaseMessagingService = firebaseMessagingService;
        this.pushNotificationService = pushNotificationService;
        this.userRepository = userRepository;
    }


    //@Scheduled(fixedDelay = 60000)
    public void sendScheduledMessages() throws FirebaseMessagingException {
        List<PushNotification> notifications = pushNotificationService.findAll();
        List<User> users = userRepository.findAll();
        List<String> tokens = new ArrayList<>();

        for (User user : users ){
            tokens.add(user.getDeviceToken());
            for (PushNotification notice : notifications) {
                String title = notice.getTitle();
                String content = notice.getNotificationMessage();

                firebaseMessagingService.sendNotificationToBatch(tokens, title, content);
            }
        }
    }
}
