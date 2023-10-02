package com.mac.m2land_cms.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Class PushNotificationRequest.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationRequest {

    private String notificationMessage;

    private LocalDateTime notificationCreate;

    private LocalDateTime notificationStart;

    private LocalDateTime notificationEnd;

    private LocalDateTime timeRemind;

    private Long notificationCount;

    private Long userId;
}
