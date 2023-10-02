package com.mac.m2land_cms.model.entity;


import com.mac.m2land_cms.model.enum_class.ShopType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;



/**
 * The class Shop.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "SHOP")
@Table(name = "shop")
public class Shop {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARACTER_ID", referencedColumnName = "CHARACTER_ID")
    private UserCharacter character;

    @Column(name = "SHOP_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private ShopType type = ShopType.COSTUME_SHOP;

    @OneToMany(mappedBy="shop", orphanRemoval = true)
    List<ShopCostume> costumeList;

    @OneToMany(mappedBy="shop", orphanRemoval = true)
    List<ShopMyRoomItem> itemList;

    public Shop(UserCharacter character) {
        this.character = character;
    }
}
