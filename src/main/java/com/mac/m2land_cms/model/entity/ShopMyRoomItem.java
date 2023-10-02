package com.mac.m2land_cms.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The class ShopMyRoomItem.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "SHOP_ROOM_ITEM")
@Table(name = "shop_room_item")
public class ShopMyRoomItem {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID",referencedColumnName = "ITEM_ID")
    MyRoomItem item;

    @ManyToOne
    @JoinColumn(name = "SHOP_ID",referencedColumnName = "ID")
    Shop shop;

    @Column(name = "IS_SOLD_OUT")
    boolean isSoldOut;
}
