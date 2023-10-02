package com.mac.m2land_cms.service.inf;

import com.mac.m2land_cms.model.request.UserCharacterRequest;
import com.mac.m2land_cms.model.response.UserCharacterResponse;

/**
 * The Class UserCharacterService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface UserCharacterService {

    /**
     *
     * @param userId
     * @return UserCharacterResponse
     */
    UserCharacterResponse getUserCharacterInfo(Long userId);


    /**
     *
     * @param request
     * @return UserCharacterResponse
     */
    UserCharacterResponse updateUserCharacter(UserCharacterRequest request);
}
