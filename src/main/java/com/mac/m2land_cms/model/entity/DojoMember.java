package com.mac.m2land_cms.model.entity;

import com.mac.m2land_cms.model.enum_class.DojoPosition;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * The class DojoMember.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "DOJO_MEMBER")
@Table(name = "dojo_member")
public class DojoMember {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CHARACTER_ID",referencedColumnName = "CHARACTER_ID",nullable = false)
    private UserCharacter character;

    @ManyToOne
    @JoinColumn(name = "DOJO_ID", nullable = false)
    private Dojo dojo;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "POSITION", nullable = false)
    private DojoPosition position;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;
}
