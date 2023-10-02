package com.mac.m2land_cms.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;



/**
 * The class RoomScrapBookEntity.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "ROOM_SCRAP_BOOK")
@Table(name = "room_scrap_book")
public class RoomScrapBookEntity {

    @Id
    @Column(name = "SCRAP_BOOK_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    private MyRoomEntity room;

    @OneToMany(mappedBy = "scrapBook")
    private List<Video> videoList;

}
