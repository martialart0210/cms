package com.mac.m2land_cms.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mac.m2land_cms.model.enum_class.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.mac.m2land_cms.model.enum_class.Status.IN_REVIEW;


/**
 * The class UserResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String username;
    private String nickName;
    private String phone;
    private String fdActvtyCode;
    private String dpcAreaCode;
    private String oneLineIntro;
    private String longIntro;
    private String lcActvtyCode;
    private String record;
    private String profileImage;
    private String userTags;
    private String lcResidence;
    private Character gender;
    private String ageRange;
    private String bankName;
    private String bankOwner;
    private String bankAccNum;
    private String memberType;
    private Boolean isUpgrading;
    private String facebookId;
    private String googleId;
    private String kakaoId;
    private String naverId;
    private Integer ansAdoptCnt;
    private Integer answeredCnt;
    private Integer questionCnt;
    private Integer psnlqCnt;
    private Integer getPsnlqCnt;
    private Integer tripnoteCnt;
    private Integer articleCnt;
    private Integer myUpvoteCnt;
    private Integer scrapedCnt;
    private Integer cumulPoint;
    private Integer currentPoint;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private String banReason;
    private int expansionCouponNumber;

    private Boolean isBanned = Boolean.FALSE;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sysCreateCharacter;

    private Status status = IN_REVIEW;

    private String role;

    private LocalDateTime lastAccess;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime suspensionStart;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime suspensionEnd;

    private String reasonSuspension;

    private boolean connectionStatus;

    private String uuid;

    private String characterName;

    private String wannabeId;

    private String appleId;

}
