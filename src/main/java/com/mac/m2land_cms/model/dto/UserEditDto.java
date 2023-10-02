package com.mac.m2land_cms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class UserEditDto.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String role;
}
