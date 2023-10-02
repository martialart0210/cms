package com.mac.m2land_cms.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuspensionRequest {

    private Long id;
    private LocalDateTime suspensionStart;
    private LocalDateTime suspensionEnd;
    private String reasonSuspension;

}
