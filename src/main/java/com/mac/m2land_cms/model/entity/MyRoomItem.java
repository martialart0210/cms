package com.mac.m2land_cms.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mac.m2land_cms.model.enum_class.ItemType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;


/**
 * The class MyRoomItem.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "MY_ROOM_ITEM")
@Table(name = "my_room_item")
public class MyRoomItem {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Long id;

    @Column(name = "ITEM_NAME", nullable = false)
    private String itemName;

    @Column(name = "ITEM_PRICE", nullable = false)
    private Integer itemPrice;

    @Column(name = "LENGTH")
    private int length;

    @Column(name = "WIDTH")
    private int width;

    @Column(name = "HEIGHT")
    private int height;

    @Column(name = "ITEM_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private ItemType type = ItemType.FURNITURE;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(mappedBy="item")
    @JsonIgnore
    private List<RoomDrawerEntity> roomDrawer;

    @OneToMany(mappedBy="item")
    @JsonIgnore
    List<ShopMyRoomItem> itemList;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final MyRoomItem item = (MyRoomItem) obj;
        return Objects.equals(this.id, item.id);
    }

    public MyRoomItem(Long id) {
        this.id = id;
    }
}
