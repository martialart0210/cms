package com.mac.m2land_cms.service.inf;

import com.mac.m2land_cms.model.dto.*;
import org.springframework.data.domain.Pageable;

import javax.management.relation.RoleNotFoundException;

/**
 * The interface UserManagementService.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
public interface UserManagementService {
    /**
     * findAll
     * @param pageable
     * @return PageResDto<AllUserManagementDto>
     */
    PageResDto<AllUserManagementDto> findAll(Pageable pageable);

    /**
     * addUser
     * @param user
     */
    void addUser(UserCreateDto user) throws RoleNotFoundException;

    /**
     * updateUser
     * @param user
     */
    void updateUser(UserEditDto user) throws RoleNotFoundException;

    /**
     * deleteUser
     * @param id
     */
    void deleteUser(Long id);

    /**
     * findAll
     * @return PageResDto<UserSuspensionDto>
     */
    PageResDto<UserSuspensionDto> findAllSuspension(Pageable pageable);

    /**
     * findAllAccess
     * @param pageable
     * @return
     */
    PageResDto<AllAccessUsersDto> findAllAccess(Pageable pageable);

    /**
     * addAccessUser
     * @param maximum
     * @param id
     */
    void addAccessUser(int maximum, Long id);
}
