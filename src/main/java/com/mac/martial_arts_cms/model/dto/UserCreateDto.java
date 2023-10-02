package com.mac.martial_arts_cms.model.dto;

import com.mac.martial_arts_cms.model.enum_class.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * The Class UserCreateDto.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private Long id;
    private String username;
    private String password;
    private String role;
}
