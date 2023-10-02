package com.mac.m2land_cms.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The Class Guild.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "GUILD")
@Table(name = "guilds")
public class Guild {

    @Id
    @Column(name = "GUILD_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    @Column(name = "GUILD_NAME", length = 150)
    private String guildName;

    @Column(name = "CHARACTER_ID")
    @JoinColumn(name = "CHARACTER_ID")
    private Long characterId;

}
