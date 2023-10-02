package com.mac.m2land_cms.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.List;



/**
 * The class QuestInfo.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "QUEST_INFO")
@Table(name = "quest_info")
public class QuestInfo {

    @Serial
    private static final long serialVersionUID = 3145254234350532564L;
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUEST_ID", nullable = false)
    private Long questInfoID;

    @Column(name = "REWARD", nullable = false)
    public int reward;

    @Column(name = "QUEST_DESCRIPTION", nullable = false)
    private String questDescription;

    @Column(name = "QUEST_NAME", nullable = false)
    public String questName;

    @OneToMany(mappedBy = "questInfo")
    List<CharacterQuestDetail> detailList;
}
