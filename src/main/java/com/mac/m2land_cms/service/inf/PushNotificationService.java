package com.mac.m2land_cms.service.inf;

import com.mac.m2land_cms.model.request.PushNotificationRequest;
import com.mac.m2land_cms.model.response.PushNotificationResponse;

/**
 * The interface PushNotificationService.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
public interface PushNotificationService {

    /**
     *
     * @param notice
     * @return NoticeRegisterRequest
     */
    PushNotificationResponse register(PushNotificationRequest notice);
}
