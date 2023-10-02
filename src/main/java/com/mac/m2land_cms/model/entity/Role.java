package com.mac.m2land_cms.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "ROLE")
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 3145254234350532564L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "ROLE_NAME", length = 20)
    private String roleName;

    @Size(max = 2000)
    @Column(name = "ROLE_DESCRIPTION", length = 2000)
    private String roleDescription;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @Size(max = 20)
    @Column(name = "CREATED_USER", length = 20)
    private String createdUser;

    @OneToMany(mappedBy = "role")
    private Set<UserRoleEntity> userRoleEntities;
}
