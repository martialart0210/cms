package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.Exceptions.UserSuspensionException;
import com.mac.martial_arts_cms.model.dto.AllUserManagementDto;
import com.mac.martial_arts_cms.model.dto.PageResDto;
import com.mac.martial_arts_cms.model.dto.UserCreateDto;
import com.mac.martial_arts_cms.model.dto.UserSuspensionDto;
import com.mac.martial_arts_cms.model.entity.User;
import com.mac.martial_arts_cms.model.enum_class.Status;
import com.mac.martial_arts_cms.repository.UserManagementRepository;
import com.mac.martial_arts_cms.service.inf.UserManagementService;
import com.mac.martial_arts_cms.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * The class UserManagementServiceImpl.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {
    private static final Logger log = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    private UserManagementRepository userManagementRepository;
    private PageUtils pageUtils;
    private MessageUtils messageUtils;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserManagementServiceImpl(UserManagementRepository userManagementRepository, PageUtils pageUtils, MessageUtils messageUtils, PasswordEncoder passwordEncoder) {
        this.userManagementRepository = userManagementRepository;
        this.pageUtils = pageUtils;
        this.messageUtils = messageUtils;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * findAll
     * @param pageable
     * @return PageResDto<AllUserManagementDto>
     */
    @Override
    public PageResDto<AllUserManagementDto> findAll(Pageable pageable) {
        Page<User> list = userManagementRepository.findAll(pageable);
        PageResDto<AllUserManagementDto> page = pageUtils.convertPageEntityToDTO(list, AllUserManagementDto.class);
        return page;
    }

    /**
     * addUser
     * @param user
     */
    @Override
    public void addUser(UserCreateDto user) {
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString().replace("-", "");

        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setUuid(strUuid);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userManagementRepository.save(userEntity);
    }

    /**
     * updateUser
     * @param userDto
     */
    @Override
    public void updateUser(UserCreateDto userDto) {
        Optional<User> user = userManagementRepository.findById(userDto.getId());
        if(user.isPresent()) {
            User userRequest = user.get();
            BeanUtils.copyProperties(userDto, userRequest);
            userRequest.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRequest.setUpdatedAt(LocalDateTime.now());
            userManagementRepository.save(userRequest);
        } else {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            throw new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        }
    }

    /**
     * deleteUser
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userManagementRepository.findById(id);
        if(user.isPresent()) {
            user.get().setStatus(Status.INACTIVATED);
            userManagementRepository.save(user.get());
        } else {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            throw new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        }

    }

    /**
     * findAll Suspension
     * @return PageResDto<UserSuspensionDto>
     */
    @Override
    public PageResDto<UserSuspensionDto> findAllSuspension(Pageable pageable) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Page<User> list = userManagementRepository.findAllSuspension(currentDateTime, pageable);
        PageResDto<UserSuspensionDto> page = pageUtils.convertPageEntityToDTO(list, UserSuspensionDto.class);
        return page;
    }
}
