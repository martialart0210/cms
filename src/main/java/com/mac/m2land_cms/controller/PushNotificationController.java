package com.mac.m2land_cms.controller;

import com.mac.m2land_cms.model.request.PushNotificationRequest;
import com.mac.m2land_cms.model.response.PushNotificationResponse;
import com.mac.m2land_cms.service.inf.PushNotificationService;
import com.mac.m2land_cms.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class PushNotificationController.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RestController
@RequestMapping(value = "/push-notification")
public class PushNotificationController extends BaseController {

    private PushNotificationService pushNotificationService;

    @Autowired
    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    /**
     * registerNotification
     * @param request
     * @return ResponseEntity<?>
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerNotification(@RequestBody PushNotificationRequest request){
        PushNotificationResponse response = pushNotificationService.register(request);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }
}
