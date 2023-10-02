package com.mac.m2land_cms.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class UserCharacterRequest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">an</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCharacterRequest {
    private Long id;
    private String characterName;
    private Long gold;
    private String guildName;
    private Long userId;
}
