package com.mac.m2land_cms.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mac.m2land_cms.model.enum_class.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Class UserAccountResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountResponse {

    private Long id;

    private boolean connectionStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sysCreateCharacter;

    private Status status;

    private String uuid;
}
