package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.UserNameExistsException;
import com.mac.m2land_cms.Exceptions.UserNotFoundException;
import com.mac.m2land_cms.model.dto.AllAccessUsersDto;
import com.mac.m2land_cms.model.dto.AllUserManagementDto;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.dto.UserCreateDto;
import com.mac.m2land_cms.model.dto.UserEditDto;
import com.mac.m2land_cms.model.dto.UserSuspensionDto;
import com.mac.m2land_cms.model.entity.Role;
import com.mac.m2land_cms.model.entity.Topic;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.entity.UserRoleEntity;
import com.mac.m2land_cms.model.enum_class.Status;
import com.mac.m2land_cms.repository.RoleRepository;
import com.mac.m2land_cms.repository.TopicRepository;
import com.mac.m2land_cms.repository.UserManagementRepository;
import com.mac.m2land_cms.repository.UserRoleRepository;
import com.mac.m2land_cms.service.inf.UserManagementService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
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
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private PageUtils pageUtils;
    private MessageUtils messageUtils;
    private PasswordEncoder passwordEncoder;
    private TopicRepository topicRepository;

    @Autowired
    public UserManagementServiceImpl(UserManagementRepository userManagementRepository, RoleRepository roleRepository,
                                     UserRoleRepository userRoleRepository, PageUtils pageUtils,
                                     MessageUtils messageUtils, PasswordEncoder passwordEncoder,
                                     TopicRepository topicRepository) {
        this.userManagementRepository = userManagementRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.pageUtils = pageUtils;
        this.messageUtils = messageUtils;
        this.passwordEncoder = passwordEncoder;
        this.topicRepository = topicRepository;
    }

    /**
     * findAll
     *
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
    public void addUser(UserCreateDto user) throws RoleNotFoundException {
        //check username exists
        Optional<User> findUser = userManagementRepository.findByUserName(user.getUsername());
        if(findUser.isPresent()) {
            throw new UserNameExistsException(messageUtils.getMessage(CommonConstants.MessageError.ER049, null));
        }

        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString().replace("-", "");

        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setUuid(strUuid);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        //Find role
        Role role= roleRepository.findByRoleName(userEntity.getRole());
        if(role == null) {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER047, null));
            throw new RoleNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER047, null));
        }
        List<Topic> topicByUser = userEntity.getTopics();
        List<Topic> topicList = topicRepository.findAll();
        if (null == topicByUser) {
            userEntity.setTopics(topicList);
        }

        userManagementRepository.save(userEntity);
        saveUserRole(strUuid, role);
    }

    /**
     * saveUserRole
     * @param strUuid
     * @param role
     */
    public void saveUserRole(String strUuid, Role role) {
        Optional<User> userEntity = userManagementRepository.findByUuid(strUuid);
        if(userEntity.isPresent()) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUser(userEntity.get());
            userRoleEntity.setRole(role);
            userRoleRepository.save(userRoleEntity);
        } else {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            throw new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        }
    }


    /**
     * updateUser
     * @param userDto
     */
    @Override
    public void updateUser(UserEditDto userDto) throws RoleNotFoundException {
        Optional<User> user = userManagementRepository.findById(userDto.getId());
        if(user.isPresent()) {
            User userRequest = user.get();
            BeanUtils.copyProperties(userDto, userRequest);
            userRequest.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRequest.setUpdatedAt(LocalDateTime.now());

            //Find role
            Role role= roleRepository.findByRoleName(userDto.getRole());
            if(role == null) {
                log.error(messageUtils.getMessage(CommonConstants.MessageError.ER047, null));
                throw new RoleNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER047, null));
            }

            userManagementRepository.save(userRequest);
            saveUserRole(user.get().getUuid(), role);
        } else {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            throw new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
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
            throw new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
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

    /**
     * findAllAccess
     * @param pageable
     * @return
     */
    @Override
    public PageResDto<AllAccessUsersDto> findAllAccess(Pageable pageable) {
        Page<User> list = userManagementRepository.findAllAccess(pageable);
        PageResDto<AllAccessUsersDto> page = pageUtils.convertPageEntityToDTO(list, AllAccessUsersDto.class);
        return page;
    }

    /**
     * addAccessUser
     * @param maximum
     * @param id
     */
    @Override
    public void addAccessUser(int maximum, Long id) {
        Optional<User> user = userManagementRepository.findById(id);
        if(user.isPresent()) {
            User userAdd = user.get();
            userAdd.setMaximumAccessUser(maximum);
            userAdd.setUpdateAccessUser(LocalDateTime.now());
            userManagementRepository.save(userAdd);
        } else {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            throw new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        }
    }
}
