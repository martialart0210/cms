package com.mac.martial_arts_cms.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Class PushNotificationResponse.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationResponse {

    private String notificationMessage;

    private String notificationCreate;

    private String notificationStart;

    private String notificationEnd;

    private String timeRemind;

    private Long notificationCount;
}
