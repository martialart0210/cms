package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.dto.AllUserManagementDto;
import com.mac.martial_arts_cms.model.dto.PageResDto;
import com.mac.martial_arts_cms.model.dto.UserCreateDto;
import com.mac.martial_arts_cms.model.dto.UserSuspensionDto;
import com.mac.martial_arts_cms.model.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    void addUser(UserCreateDto user);

    /**
     * updateUser
     * @param user
     */
    void updateUser(UserCreateDto user);

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
}
