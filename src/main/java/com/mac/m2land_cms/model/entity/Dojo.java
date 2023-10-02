package com.mac.m2land_cms.model.entity;

import com.mac.m2land_cms.model.enum_class.DojoStatus;
import com.mac.m2land_cms.model.enum_class.SubscriptionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = "dojo")
public class Dojo {
    private static final long serialVersionUID = 7629287212050058050L;
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "GUILD_LEADER", unique = true)
    private String guildLeader;

    @Size(max = 150)
    @Column(name = "DOJO_NAME", length = 150)
    private String dojoName;

    @Column(name = "LIMIT_SUB")
    private Integer limitSub;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "SUBSCRIPTION_TYPE")
    private SubscriptionType subscriptionType;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "DOJO_SYMBOL_ID")
    private Long symbolId;

    @Column(name = "DOJO_COLOR_CODE")
    private String dojoColorCode;

    @Column(name = "DOJO_NOTICE")
    private String dojoNotice;

    @NotNull
    @Column(name = "DOJO_STATUS", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DojoStatus status = DojoStatus.IN_OPERATION;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CLOSED_DATE")
    private LocalDateTime closedDate;

    @OneToMany(mappedBy = "dojo", orphanRemoval = true)
    @OrderBy(value = "createdAt asc ")
    List<DojoRequest> dojoRequestList;

    @OneToMany(mappedBy = "dojo", orphanRemoval = true)
    @OrderBy(value = "position asc ")
    List<DojoMember> dojoMemberList;

    @OneToMany(mappedBy = "dojo", orphanRemoval = true)
    List<DojoLog> dojoLogs;
}
