package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.entity.User;
import com.mac.martial_arts_cms.model.response.UserAccountResponse;
import com.mac.martial_arts_cms.model.response.UserResponse;
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
     * @param searchCondition
     * @param searchWord
     * @return UserResponse
     */
    UserResponse searchUserAccount(String searchCondition, String searchWord);

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
