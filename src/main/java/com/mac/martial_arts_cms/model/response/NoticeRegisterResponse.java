package com.mac.martial_arts_cms.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class NoticeRegisterResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeRegisterResponse {

    private String noticeMessage;

    private String noticeCreate;

    private String noticeStart;

    private String noticeEnd;

    private String timeRemind;

    private Long noticeCount;
}
