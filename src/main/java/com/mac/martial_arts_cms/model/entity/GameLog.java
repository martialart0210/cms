package com.mac.martial_arts_cms.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The Class GameLog.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "GAMELOG")
@Table(name = "gameLogs")
public class GameLog {

    @Id
    @Column(name = "GAMELOG_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LABEL", length = 150)
    private String label;

    @Column(name = "TIMESTAMP", length = 150)
    private LocalDateTime timestamp;

    @Column(name = "DETAIL", length = 150)
    private String detail;

    @Column(name = "USER_ID", length = 150)
    private Long userId;

}
