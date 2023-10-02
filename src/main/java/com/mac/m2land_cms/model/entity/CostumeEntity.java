package com.mac.m2land_cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mac.m2land_cms.model.enum_class.CostumeType;
import com.mac.m2land_cms.model.enum_class.GenderEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * The class CostumeEntity.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "COSTUME")
@Table(name = "costume")
public class CostumeEntity {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COSTUME_ID", nullable = false)
    private Long costumeId;

    @Column(name = "COSTUME_NAME", nullable = false)
    private String costumeName;

    @Column(name = "COSTUME_PRICE", nullable = false)
    private Integer costumePrice;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "COSTUME_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private CostumeType type = CostumeType.HAIR;

    @Column(name = "GENDER")
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender = GenderEnum.MALE;

    @OneToMany(mappedBy="costume")
    @JsonIgnore
    List<ShopCostume> shopList;

    @OneToMany(mappedBy="item")
    @JsonIgnore
    List<WardrobeItem> wardrobeItemList;
}
