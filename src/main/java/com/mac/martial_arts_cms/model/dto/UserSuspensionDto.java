package com.mac.martial_arts_cms.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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

    private String role;
}
