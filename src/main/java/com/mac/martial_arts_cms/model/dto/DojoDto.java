package com.mac.martial_arts_cms.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mac.martial_arts_cms.model.enum_class.DojoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Class DojoDto.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DojoDto {
    private String id;

    private String dojoName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedDate;

    private int member;

    private DojoStatus status;
}
