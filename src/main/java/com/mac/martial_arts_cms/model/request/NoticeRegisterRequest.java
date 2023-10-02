package com.mac.martial_arts_cms.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Class NoticeRegisterRequest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeRegisterRequest {

    private String noticeMessage;

    private LocalDateTime noticeCreate;

    private LocalDateTime noticeStart;

    private LocalDateTime noticeEnd;

    private LocalDateTime timeRemind;

    private Long noticeCount;

    private Long userId;
}
