package com.mac.m2land_cms.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
