package com.mac.m2land_cms.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * The Class Video.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "VIDEO")
@Table(name = "videos")
public class Video {
    private static final long serialVersionUID = 7629287212050058050L;
    @Id
    @Column(name = "VIDEO_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "VIDEO_LINK", length = 1000)
    private String videoLink;

    @Column(name = "PLAYLIST")
    private String playlist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SCRAP_BOOK_ID", referencedColumnName = "SCRAP_BOOK_ID")
    private RoomScrapBookEntity scrapBook;
}
