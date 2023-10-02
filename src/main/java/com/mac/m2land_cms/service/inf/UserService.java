package com.mac.m2land_cms.service.inf;

import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.response.UserAccountResponse;
import com.mac.m2land_cms.model.response.UserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;

/**
 * The interface User service.
 */
public interface UserService extends UserDetailsService {
    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    User getUserByUsername(String username);


    /**
     *
     * @param searchProvider
     * @param searchUsername
     * @return UserResponse
     */
    PageResDto<UserResponse> searchUserAccount(String searchProvider, String searchUsername, Pageable pageable);

    /**
     *
     * @param username
     */
    void releaseSuspension(String username);


    /**
     *
     * @param username
     * @return UserAccountResponse
     */
    UserAccountResponse getUserAccountInfo(String username);

    /**
     *
     * @param id
     * @param suspensionStart
     * @param suspensionEnd
     * @param reasonSuspension
     */

    void UpdateSuspension(Long id,LocalDateTime suspensionStart,
                          LocalDateTime suspensionEnd, String reasonSuspension);
}
