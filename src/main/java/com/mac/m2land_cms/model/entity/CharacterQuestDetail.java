package com.mac.m2land_cms.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The class CharacterQuestDetail.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "CHARACTER_QUEST_DETAIL")
@Table(name = "character_quest_detail")
public class CharacterQuestDetail {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PERFORMED_COUNT", nullable = false)
    private int performedCount;

    @Column(name = "MAX_PERFORMED", nullable = false)
    private int maxPerformed;

    @Column(name = "IS_ACCEPT", nullable = false)
    private boolean isAccept;

    @Column(name = "IS_COMPLETED", nullable = false)
    private boolean isCompleted;

    @Column(name = "IS_FINISHED", nullable = false)
    private boolean isFinished;

    @ManyToOne
    @JoinColumn(name = "QUEST_ID", nullable = false)
    private QuestInfo questInfo;

    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID", nullable = false)
    private UserCharacter character;
}
