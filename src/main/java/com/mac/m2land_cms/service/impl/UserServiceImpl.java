package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.SuspensionException;
import com.mac.m2land_cms.Exceptions.UserNotFoundException;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.dto.UserDto;
import com.mac.m2land_cms.model.entity.Role;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.enum_class.Status;
import com.mac.m2land_cms.model.response.UserAccountResponse;
import com.mac.m2land_cms.model.response.UserResponse;
import com.mac.m2land_cms.repository.RoleRepository;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.repository.UserSpecifications;
import com.mac.m2land_cms.service.inf.UserService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import com.mac.m2land_cms.utils.TokenUtils;
import com.mac.m2land_cms.utils.UserAuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * The Class UserServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    private final TokenUtils tokenUtils;

    private final HttpServletRequest request;

    private final ModelMapper modelMapper;
    private final MessageUtils messageUtils;

    private final UserAuthUtils userAuthUtils;

    private static final String SEPARATOR = "<>";

    private final UserSpecifications userSpecifications;
    private final PageUtils pageUtils;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder, TokenUtils tokenUtils, HttpServletRequest request, ModelMapper modelMapper, MessageUtils messageUtils, @Lazy UserAuthUtils userAuthUtils, UserSpecifications userSpecifications, PageUtils pageUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.tokenUtils = tokenUtils;
        this.request = request;
        this.modelMapper = modelMapper;
        this.messageUtils = messageUtils;
        this.userAuthUtils = userAuthUtils;
        this.userSpecifications = userSpecifications;
        this.pageUtils = pageUtils;
    }

    /**
     * Load user by username
     *
     * @param username
     * @return userdetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUsersByUsernameOrEmailOrPhone(username, username, username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER038, null));
        }
        if (user.get().getIsBanned()) {
            throw new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER039, null));
        }
        if (user.get().getStatus().equals(Status.INACTIVATED)) {
            log.info(messageUtils.getMessage(CommonConstants.MessageError.ER040, null));
            throw new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER040, null));
        }
        List<Role> userRoles = roleRepository.getRolesUser(user.get().getId());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUsersByUsername(username).orElse(null);
    }

    /**
     * @param searchProvider
     * @param searchUsername
     * @return UserResponse
     */
    @Override
    public PageResDto<UserResponse> searchUserAccount(String searchProvider, String searchUsername, Pageable pageable) {

        Page<User> users = userRepository.searchUserAccount(searchProvider,searchUsername,pageable);
        if (users.isEmpty()) {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            throw new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        }
        return pageUtils.convertPageEntityToDTO(users,UserResponse.class);
    }

    /**
     * @param username
     */
    @Override
    public void releaseSuspension(String username) {
        Optional<User> user = userRepository.findUsersByUsername(username);
        if (user.isPresent()) {
            User currentUser = user.get();
            currentUser.setSuspensionStart(null);
            currentUser.setSuspensionEnd(null);
            currentUser.setReasonSuspension(null);
            BeanUtils.copyProperties(UserDto.class, currentUser);
            userRepository.save(currentUser);
        } else {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            throw new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        }
    }

    /**
     * @param username
     * @return UserAccountResponse
     */
    @Override
    public UserAccountResponse getUserAccountInfo(String username) {
        User user = userRepository.findUsersByUsername(username).orElseThrow(() -> {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            return new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        });
        return modelMapper.map(user,UserAccountResponse.class);
    }

    /**
     *
     * @param id
     * @param suspensionStart
     * @param suspensionEnd
     * @param reasonSuspension
     */
    @Override
    public void UpdateSuspension(Long id, LocalDateTime suspensionStart, LocalDateTime suspensionEnd, String reasonSuspension) {
        if (suspensionStart.isAfter(suspensionEnd)){
            throw new SuspensionException(messageUtils.getMessage(CommonConstants.MessageError.ER044, null));
        }
        userRepository.findById(id).orElseThrow(() -> {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            return new UsernameNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        });
        userRepository.UpdateSuspension(id, suspensionStart, suspensionEnd, reasonSuspension);
    }
}
