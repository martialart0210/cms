package com.mac.martial_arts_cms.model.entity;

import com.mac.martial_arts_cms.model.enum_class.DojoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Class Dojo.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "DOJO")
@Table(name = "dojos")
public class Dojo {
    private static final long serialVersionUID = 7629287212050058050L;
    @Id
    @Column(name = "GUILD_LEADER")
    private String id;

    @Size(max = 150)
    @Column(name = "DOJO_NAME", length = 150)
    private String dojoName;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "CLOSED_DATE")
    private LocalDateTime closedDate;

    @Column(name = "MEMBERS")
    private int member;

    @NotNull
    @Column(name = "DOJO_STATUS", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DojoStatus status;

}
