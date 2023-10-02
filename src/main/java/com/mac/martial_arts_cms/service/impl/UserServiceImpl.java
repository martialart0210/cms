package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.Exceptions.SuspensionException;
import com.mac.martial_arts_cms.Exceptions.UserNotFoundException;
import com.mac.martial_arts_cms.model.dto.UserDto;
import com.mac.martial_arts_cms.model.entity.Role;
import com.mac.martial_arts_cms.model.entity.User;
import com.mac.martial_arts_cms.model.enum_class.Status;
import com.mac.martial_arts_cms.model.response.UserAccountResponse;
import com.mac.martial_arts_cms.model.response.UserResponse;
import com.mac.martial_arts_cms.repository.RoleRepository;
import com.mac.martial_arts_cms.repository.UserRepository;
import com.mac.martial_arts_cms.repository.UserSpecifications;
import com.mac.martial_arts_cms.service.inf.UserService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import com.mac.martial_arts_cms.utils.MessageUtils;
import com.mac.martial_arts_cms.utils.TokenUtils;
import com.mac.martial_arts_cms.utils.UserAuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MessageUtils messageUtils;

    private UserAuthUtils userAuthUtils;

    private static final String SEPARATOR = "<>";


    @Autowired
    public UserServiceImpl(@Lazy UserAuthUtils userAuthUtils) {
        this.userAuthUtils = userAuthUtils;
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
     * @param searchCondition
     * @param searchWord
     * @return UserResponse
     */
    @Override
    public UserResponse searchUserAccount(String searchCondition, String searchWord) {

        Specification<User> spec = UserSpecifications.compareKeywords(searchCondition, searchWord);

        List<User> users = userRepository.findAll(spec);

        if (users.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return modelMapper.map(users.get(0), UserResponse.class);
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
        return mapToUserAccountResponse(user);
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

    /**
     * @param user
     * @return UserAccountResponse
     */
    private UserAccountResponse mapToUserAccountResponse(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String CreateAtTimeStamp = user.getCreatedAt().format(formatter);
        String CharacterCreateAtTimestamp = user.getSysCreateCharacter().format(formatter);
        return new UserAccountResponse(user.getId(),
                user.isConnectionStatus(),
                CreateAtTimeStamp,
                CharacterCreateAtTimestamp,
                user.getStatus(),
                user.getUuid());
    }
}
