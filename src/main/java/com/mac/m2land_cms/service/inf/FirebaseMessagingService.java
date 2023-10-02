package com.mac.m2land_cms.service.inf;

import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.List;


/**
 * The interface FirebaseMessagingService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface FirebaseMessagingService {

    /**
     *
     * @param tokens
     * @param title
     * @param body
     */
    void sendNotificationToBatch(List<String> tokens, String title, String body) throws FirebaseMessagingException;
}
