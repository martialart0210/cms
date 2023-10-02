package com.mac.martial_arts_cms.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mac.martial_arts_cms.model.enum_class.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * The Class AllUserManagementDto.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUserManagementDto {
    private Long id;
    private String username;
    private String role;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastAccess;
}
