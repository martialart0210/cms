package com.mac.m2land_cms.service.inf;

import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.List;


/**
 * The interface TopicSubscriptionService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">an</a>
 */
public interface TopicSubscriptionService {

    boolean subscribeToTopic(List<String> token, String topic) throws FirebaseMessagingException;
}
