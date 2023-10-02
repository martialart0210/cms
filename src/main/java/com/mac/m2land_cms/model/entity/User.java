package com.mac.m2land_cms.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mac.m2land_cms.model.enum_class.AuthProvider;
import com.mac.m2land_cms.model.enum_class.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.mac.m2land_cms.model.enum_class.Status.IN_REVIEW;


/**
 * The class User.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "USER")
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 7629287212050058050L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FD_ACTVTY_CODE")
    private String fdActvtyCode;

    @Column(name = "SPC_AREA_CODE")
    private String dpcAreaCode;

    @Column(name = "ONE_LINE_INTRO")
    private String oneLineIntro;

    @Column(name = "LONG_INTRO")
    private String longIntro;

    @Column(name = "LC_ACTVTY_CODE")
    private String lcActvtyCode;

    @Column(name = "RECORD")
    private String record;

    @Column(name = "PROFILE_IMAGE")
    private String profileImage;

    @Column(name = "USER_TAGS")
    private String userTags;

    @Column(name = "LC_RESIDENCE")
    private String lcResidence;

    @Column(name = "GENDER")
    private Character gender;

    @Column(name = "AGE_RANGE")
    private String ageRange;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_OWNER")
    private String bankOwner;

    @Column(name = "BANK_ACC_NUM")
    private String bankAccNum;

    @Column(name = "MEMBER_TYPE")
    private String memberType;

    @Column(name = "IS_UPGRADING")
    private Boolean isUpgrading;

    @Column(name = "ANS_ADOPT_CNT")
    private Integer ansAdoptCnt;

    @Column(name = "ANSWERED_CNT")
    private Integer answeredCnt;

    @Column(name = "QUESTION_CNT")
    private Integer questionCnt;

    @Column(name = "PSNLQ_CNT")
    private Integer psnlqCnt;

    @Column(name = "GET_PSNLQ_CNT")
    private Integer getPsnlqCnt;

    @Column(name = "TRIPNOTE_CNT")
    private Integer tripnoteCnt;

    @Column(name = "ARTICLE_CNT")
    private Integer articleCnt;

    @Column(name = "MY_UPVOTE_CNT")
    private Integer myUpvoteCnt;

    @Column(name = "SCRAPED_CNT")
    private Integer scrapedCnt;

    @Column(name = "CUMUL_POINT")
    private Integer cumulPoint;

    @Column(name = "CURRENT_POINT")
    private Integer currentPoint;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "BAN_REASON")
    private String banReason;

    @Column(name = "IS_BANNED")
    private Boolean isBanned = false;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user", orphanRemoval = true)
    private UserCharacter character;

    @Column(name = "SYS_CREATED_CHARACTER")
    private LocalDateTime sysCreateCharacter;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private Status status = IN_REVIEW;

    @Size(max = 20)
    @Column(name = "ROLE", length = 20)
    private String role;

    @Column(name = "LAST_ACCESS")
    private LocalDateTime lastAccess;

    @Column(name = "OFF_TIME")
    private LocalDateTime offTime;

    @Column(name = "SUSPENSION_START")
    private LocalDateTime suspensionStart;

    @Column(name = "SUSPENSION_END")
    private LocalDateTime suspensionEnd;

    @Column(name = "REASON_SUSPENSION")
    private String reasonSuspension;

    @Column(name = "CONNECTION_STATUS")
    private boolean connectionStatus;

    @Size(max = 32)
    @Column(name = "UUID", length = 32)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROVIDER")
    private AuthProvider provider;

    @Column(name = "IMAGE_URL", length = 2000)
    private String imageUrl;

    @Column(name = "PROVIDER_ID")
    private String providerId;


    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Topic> topics;

    @Column(name = "DEVICE_TOKEN", length = 2000)
    private String deviceToken;

    @Column(name = "MAXIMUM_ACCESS_USER")
    private Integer maximumAccessUser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_ACCESS_USER")
    private LocalDateTime updateAccessUser;

}
