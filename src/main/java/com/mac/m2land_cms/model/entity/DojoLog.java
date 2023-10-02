package com.mac.m2land_cms.model.entity;

import com.mac.m2land_cms.model.enum_class.DojoLogType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "DOJO_LOG")
@Table(name = "dojo_log")
public class DojoLog {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DOJO_ID", nullable = false)
    private Dojo dojo;


    @Column(name = "LOG_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private DojoLogType dojoLogType;

    @Column(name = "PARAMETER")
    private String parameter;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

}
