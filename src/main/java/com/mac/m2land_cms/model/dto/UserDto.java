package com.mac.m2land_cms.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty("userId")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("roleName")
    private String role;

    @NotBlank(message = "Firstname cannot be blank")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "Lastname cannot be blank")
    @JsonProperty("lastName")
    private String lastName;

    @Email(message = "Email invalid")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Phone numbers cannot be blank")
    @JsonProperty("phone")
    private String phone;

    @JsonProperty("otp")
    private String otp;
}
