package com.mac.martial_arts_cms.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The Class Figure.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "FIGURE")
@Table(name = "figures")
public class Figure {

    @Id
    @Column(name = "CHARACTER_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    @Column(name = "CHARACTER_NAME", length = 150)
    private String characterName;

    @Column(name = "GOLD")
    private Long gold;

    @Size(max = 150)
    @Column(name = "GUILD_NAME", length = 150)
    private String guildName;

    @Column(name = "USER_ID")
    private Long userId;
}