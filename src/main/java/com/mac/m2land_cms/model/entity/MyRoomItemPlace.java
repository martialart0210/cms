package com.mac.m2land_cms.model.entity;

import com.mac.m2land_cms.model.enum_class.ItemType;
import jakarta.persistence.*;
import lombok.*;


/**
 * The class MyRoomItemPlace.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "MY_ROOM_ITEM_PLACE")
@Table(name = "my_room_item_place")
public class MyRoomItemPlace {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ITEM_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private ItemType type = ItemType.FURNITURE;

    @JoinColumn(name = "ROTATE_NUMBER")
    private Integer rotateNumber;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private MyRoomItem item;

    @JoinColumn(name = "IS_FLOOR_ITEM")
    private Boolean isFloorItem;

    @Column(name = "POS_X")
    private Float posX;

    @Column(name = "POS_Y")
    private Float posY;

    @Column(name = "POS_Z")
    private Float posZ;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private MyRoomEntity myRoom;

}
