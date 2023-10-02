package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.request.PushNotificationRequest;
import com.mac.martial_arts_cms.model.response.NoticeRegisterResponse;
import com.mac.martial_arts_cms.model.response.PushNotificationResponse;

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
