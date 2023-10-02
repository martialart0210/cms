package com.mac.martial_arts_cms.model.response;

import com.mac.martial_arts_cms.model.enum_class.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;


/**
 * The class UserResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String uuid;

    private String username;

    private String password;

    private Boolean banned = false;

    private String banReason;

    private Instant banAt;

    private String lastIp;

    private Instant lastLogin;

    private String lastGeoLat;

    private String lastGeoLng;

    private Instant lastGeoAt;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Character gender;

    private LocalDate dob;

    private String avatar;

    private Instant sysCreatedAt;

    private String sysCreatedBy;

    private Instant sysUpdatedAt;

    private String sysUpdatedBy;

    private Status status;

    private Boolean isReady = false;

    private String role;

    private Instant lastAccess;

}
