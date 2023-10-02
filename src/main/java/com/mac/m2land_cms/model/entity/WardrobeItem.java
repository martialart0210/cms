package com.mac.m2land_cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


/**
 * The class WardrobeItem.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "WARDROBE_ITEM")
@Table(name = "wardrobe_item")
public class WardrobeItem implements Serializable {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = CostumeEntity.class)
    @JoinColumn(name="ITEM_ID")
    CostumeEntity item;

    @Column(name = "IS_EQUIPPED", nullable = false)
    private boolean isEquipped;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = MyRoomEntity.class)
    @JoinColumn(name="ROOM_ID")
    @JsonIgnore
    private MyRoomEntity room;


}
