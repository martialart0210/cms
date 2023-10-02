package com.mac.m2land_cms.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The class MiniGame.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "MINI_GAME")
@Table(name = "mini_game")
public class MiniGame {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REWARD")
    private BigInteger reward;

    @Column(name = "RECORD_REWARD")
    private Integer recordReward;

    @OneToMany(mappedBy = "miniGame")
    private List<MiniGameRecord> gameRecords;

}
