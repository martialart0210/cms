package com.mac.m2land_cms.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mac.m2land_cms.model.enum_class.DojoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    private String guildLeader;

    private String dojoName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedDate;

    private Integer member;

    private Integer limitSub;

    private DojoStatus status;
}
