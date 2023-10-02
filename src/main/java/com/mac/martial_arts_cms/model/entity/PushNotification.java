package com.mac.martial_arts_cms.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The Class PushNotification.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "PUSHNOTIFICATION")
@Table(name = "push_notification")
public class PushNotification implements Serializable {
    private static final long serialVersionUID = 7629287212050058050L;

    @Id
    @Column(name = "NOTIFICATION_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOTIFICATION_MESSAGE")
    private String notificationMessage;

    @Column(name = "NOTIFICATION_CREATE")
    private LocalDateTime notificationCreate;

    @Column(name = "NOTIFICATION_START")
    private LocalDateTime notificationStart;

    @Column(name = "NOTIFICATION_END")
    private LocalDateTime notificationEnd;

    @Column(name = "TIME_REMIND")
    private LocalDateTime timeRemind;

    @Column(name = "NOTIFICATION_COUNT")
    private Long notificationCount;

    @Column(name = "USER_ID", length = 150)
    private Long userId;
}
