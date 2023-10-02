package com.mac.m2land_cms.model.entity;

import com.mac.m2land_cms.model.enum_class.GenderEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;


/**
 * The Class UserCharacter.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "UserCharacter")
@Table(name = "user_character")
public class UserCharacter {

    @ToString.Include
    @Id
    @Column(name = "CHARACTER_ID", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "character")
    List<CharacterQuestDetail> detailList;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, orphanRemoval = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    private MyRoomEntity room;

    @Column(name = "GOLD")
    private BigInteger gold;

    @Size(max = 150)
    @Column(name = "CHARACTER_NAME", length = 150)
    private String characterName;

    @Column(name = "GENDER")
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender = GenderEnum.MALE;

    @Column(name = "CHARACTER_MODEL")
    private String characterModel;

    @Column(name = "EXPANSION_COUPON_NUMBER")
    private int expansionCouponNumber = 0;

    @Column(name = "SCRAPBOOK_NUMBER")
    private int scrapbookNumber = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COSTUME_HAIR", referencedColumnName = "COSTUME_ID")
    private CostumeEntity costumeHair;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COSTUME_TOP", referencedColumnName = "COSTUME_ID")
    private CostumeEntity costumeTop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COSTUME_SHOE", referencedColumnName = "COSTUME_ID")
    private CostumeEntity costumeShoe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COSTUME_BOTTOM", referencedColumnName = "COSTUME_ID")
    private CostumeEntity costumeBottom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COSTUME_ACCESSORY", referencedColumnName = "COSTUME_ID")
    private CostumeEntity costumeAccessory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character", orphanRemoval = true)
    private List<MiniGameRecord> gameRecords;

    @OneToMany(mappedBy = "characterId", orphanRemoval = true)
    private List<Contact> contactList;

    @OneToMany(mappedBy = "contactId", orphanRemoval = true)
    private List<Contact> inContacts;

    @OneToMany(mappedBy = "character", orphanRemoval = true)
    List<DojoRequest> dojoRequestList;

    @OneToOne(mappedBy = "character", orphanRemoval = true)
    DojoMember dojoMember;

    @OneToMany(mappedBy = "character", orphanRemoval = true)
    List<CharacterQuestDetail> questDetailList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character", orphanRemoval = true)
    private List<Shop> shop;
}