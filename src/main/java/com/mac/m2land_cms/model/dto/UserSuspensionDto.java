package com.mac.m2land_cms.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The Class UserSuspensionDto.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSuspensionDto {
    private String uuid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime suspensionStart;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime suspensionEnd;

    private String reasonSuspension;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private String username;
}
