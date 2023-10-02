package com.mac.m2land_cms.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import com.mac.m2land_cms.service.inf.TopicSubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The class TopicSubscriptionServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">an</a>
 */
@Service
public class TopicSubscriptionServiceImpl implements TopicSubscriptionService {

    /**
     * @param tokens
     * @param topic
     * @return boolean
     */
    @Override
    public boolean subscribeToTopic(List<String> tokens, String topic) {
        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(tokens, topic);

            if (response != null && response.getSuccessCount() > 0) {
                System.out.println("Đăng ký topic thành công: " + topic);
                return true;
            } else {
                System.out.println("Đăng ký topic thất bại: " + topic);
                return false;
            }
        } catch (FirebaseMessagingException e) {
            System.out.println("Lỗi khi đăng ký topic: " + e.getMessage());
            return false;
        }
    }
}
