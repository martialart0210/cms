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
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * The Class Notice.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "NOTICE")
@Table(name = "notices")
public class Notice implements Serializable {

    private static final long serialVersionUID = 7629287212050058050L;

    @Id
    @Column(name = "NOTICE_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOTICE_MESSAGE")
    private String noticeMessage;

    @Column(name = "NOTICE_CREATE")
    private LocalDateTime noticeCreate;

    @Column(name = "NOTICE_START")
    private LocalDateTime noticeStart;

    @Column(name = "NOTICE_END")
    private LocalDateTime noticeEnd;

    @Column(name = "TIME_REMIND")
    private LocalDateTime timeRemind;

    @Column(name = "NOTICE_COUNT")
    private Long noticeCount;

    @Column(name = "USER_ID", length = 150)
    private Long userId;

}
