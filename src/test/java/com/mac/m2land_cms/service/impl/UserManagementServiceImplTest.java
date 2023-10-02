package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.UserNotFoundException;
import com.mac.m2land_cms.model.dto.*;
import com.mac.m2land_cms.model.entity.Role;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.entity.UserRoleEntity;
import com.mac.m2land_cms.model.enum_class.Status;
import com.mac.m2land_cms.repository.RoleRepository;
import com.mac.m2land_cms.repository.UserManagementRepository;
import com.mac.m2land_cms.repository.UserRoleRepository;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The Class UserManagementServiceImplTest.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserManagementServiceImplTest {

    @Mock
    private UserManagementRepository repository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private MessageUtils messageUtils;

    @Autowired
    private PageUtils pageUtils;

    @Mock
    private PageUtils pageUtilsMock;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserManagementServiceImpl userManagementService;

    @Before
    public void setUp() {
        when(messageUtils.getMessage(any(), any())).thenReturn("Error message");
    }
    @Test
    public void findAll() {
        LocalDateTime dateTime = LocalDateTime.now();

        //Actual
        Pageable sizePagination = PageRequest.of(0, 5);
        User user = new User();
        user.setId(1l);
        user.setUsername("an");
        user.setRole("Admin");
        user.setName("an1");
        user.setCreatedAt(dateTime);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        Page<User> userPageActual = new PageImpl<>(userList, sizePagination, userList.size());
        PageResDto<AllUserManagementDto> pageResDtoActual = pageUtils.convertPageEntityToDTO(userPageActual, AllUserManagementDto.class);

        //Expected
        ContentPageDto contentPageDto = new ContentPageDto(1,1,5,0);
        AllUserManagementDto userManagementDto = new AllUserManagementDto(1l,"an1", "an", "Admin", dateTime, null);
        List<AllUserManagementDto> userManagementDtos = new ArrayList<>();
        userManagementDtos.add(userManagementDto);
        PageResDto<AllUserManagementDto> pageResDto = new PageResDto<>(userManagementDtos, contentPageDto);

        when(repository.findAll(sizePagination)).thenReturn(userPageActual);
        when(pageUtilsMock.convertPageEntityToDTO(userPageActual, AllUserManagementDto.class)).thenReturn(pageResDtoActual);
        userManagementService.findAll(sizePagination);


        assertEquals(pageResDto, pageResDtoActual);
    }

    @Test
    public void addUser() throws RoleNotFoundException {

        //Actual
        UserCreateDto user = new UserCreateDto("an", "Andong", "123", "Admin");
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString().replace("-", "");

        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setUuid(strUuid);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        Role role = new Role(1, "Admin", null, null, null, null);
        when(roleRepository.findByRoleName(user.getRole())).thenReturn(role);
        when(repository.save(userEntity)).thenReturn(userEntity);


        saveUserRole(userEntity.getUuid(), role);

        assertEquals("an", userEntity.getName());
        assertEquals("Andong", userEntity.getUsername());
    }

    @Test(expected = UserNotFoundException.class)
    public void addUserNotFoundUserInSaveUserRole() throws RoleNotFoundException {

        //Actual
        UserCreateDto user = new UserCreateDto("an", "Andong", "123", "Admin");
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString().replace("-", "");

        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setUuid(strUuid);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        Role role = new Role(1, "Admin", null, null, null, null);
        when(roleRepository.findByRoleName(user.getRole())).thenReturn(role);
        when(repository.save(userEntity)).thenReturn(userEntity);
        userManagementService.addUser(user);

        assertEquals("an", userEntity.getName());
        assertEquals("Andong", userEntity.getUsername());
    }

    @Test(expected = RoleNotFoundException.class)
    public void addUserRoleNotFound() throws RoleNotFoundException {
        //Actual
        UserCreateDto user = new UserCreateDto("an", "Andong", "123", "Adminaaa");
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString().replace("-", "");

        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setUuid(strUuid);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        when(roleRepository.findByRoleName(user.getRole())).thenReturn(null);
        userManagementService.addUser(user);
    }

    @Test
    public void updateUser(){
        //Actual
        UserEditDto userEditDto = new UserEditDto(1l,"an", "Andong", "123", "Admin");
        User user = new User();
        user.setId(1l);
        user.setName("Dong Phuoc An");
        user.setUsername("Andong");
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        BeanUtils.copyProperties(userEditDto, user);
        user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        Role role = new Role(1, "Admin", null, null, null, null);
        when(roleRepository.findByRoleName(user.getRole())).thenReturn(role);
        when(repository.save(user)).thenReturn(user);
        saveUserRole(user.getUuid(), role);
    }

    @Test(expected = UserNotFoundException.class)
    public void updateUserNotFoundUserInSaveUserRole() throws RoleNotFoundException {
        //Actual
        UserEditDto userEditDto = new UserEditDto(1l,"an", "Andong", "123", "Admin");
        User user = new User();
        user.setId(1l);
        user.setName("Dong Phuoc An");
        user.setUsername("Andong");
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        BeanUtils.copyProperties(userEditDto, user);
        user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        Role role = new Role(1, "Admin", null, null, null, null);
        when(roleRepository.findByRoleName(user.getRole())).thenReturn(role);
        when(repository.save(user)).thenReturn(user);
        userManagementService.updateUser(userEditDto);

        assertEquals("an", user.getName());
        assertEquals("Andong", user.getUsername());
    }

    @Test(expected = RoleNotFoundException.class)
    public void updateUserRoleNotFound() throws RoleNotFoundException {
        //Actual
        UserEditDto userEditDto = new UserEditDto(1l,"an", "Andong", "123", "Adminasa");
        User user = new User();
        user.setId(1l);
        user.setName("Dong Phuoc An");
        user.setUsername("Andong");
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        BeanUtils.copyProperties(userEditDto, user);
        user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());
        when(roleRepository.findByRoleName(user.getRole())).thenReturn(null);
        userManagementService.updateUser(userEditDto);
    }

    @Test(expected = UserNotFoundException.class)
    public void updateUserNotFound() throws RoleNotFoundException {
        UserEditDto userEditDto = new UserEditDto(155l,"an", "Andong", "123", "Admin");
        when(repository.findById(userEditDto.getId())).thenReturn(Optional.empty());
        userManagementService.updateUser(userEditDto);
    }

    @Test
    public void deleteUser() {
        Long userId = 1l;
        LocalDateTime dateTime = LocalDateTime.now();
        User user = new User();
        user.setId(1l);
        user.setUsername("an");
        user.setRole("Admin");
        user.setCreatedAt(dateTime);
        user.setUuid("1234");
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        user.setStatus(Status.INACTIVATED);
        userManagementService.deleteUser(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUserNotFound() {
        Long userId = 1l;
        LocalDateTime dateTime = LocalDateTime.now();
        User user = new User();
        user.setId(1l);
        user.setUsername("an");
        user.setRole("Admin");
        user.setCreatedAt(dateTime);
        user.setUuid("1234");
        when(repository.findById(userId)).thenReturn(Optional.empty());
        userManagementService.deleteUser(userId);
    }

    @Test
    public void findAllSuspension(){
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime create = LocalDateTime.of(2008,11,15,7,0,0);
        LocalDateTime end = LocalDateTime.of(2024,11,15,7,0,0);
        Pageable sizePagination = PageRequest.of(0, 5);
        User user = new User();
        user.setCreatedAt(dateTime);
        user.setUuid("80d59e5409244cf9af8782c8cb070c54");
        user.setSuspensionStart(create);
        user.setSuspensionEnd(end);
        user.setReasonSuspension(null);
        user.setRole("Admin");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        Page<User> userPageActual = new PageImpl<>(userList, sizePagination, userList.size());
        PageResDto<UserSuspensionDto> pageResDtoActual = pageUtils.convertPageEntityToDTO(userPageActual, UserSuspensionDto.class);

        //Expected
        ContentPageDto contentPageDto = new ContentPageDto(1,1,5,0);
        UserSuspensionDto userSuspensionDto = new UserSuspensionDto("80d59e5409244cf9af8782c8cb070c54", create, end,null,dateTime, "Admin");
        List<UserSuspensionDto> userSuspensionDtos = new ArrayList<>();
        userSuspensionDtos.add(userSuspensionDto);
        PageResDto<UserSuspensionDto> pageResDto = new PageResDto<>(userSuspensionDtos, contentPageDto);

        when(repository.findAllSuspension(dateTime, sizePagination)).thenReturn(userPageActual);
        when(pageUtilsMock.convertPageEntityToDTO(userPageActual, UserSuspensionDto.class)).thenReturn(pageResDtoActual);
        userManagementService.findAllSuspension(sizePagination);

        assertEquals(pageResDto, pageResDtoActual);
    }

    private void saveUserRole(String strUuid, Role role) {
        LocalDateTime dateTime = LocalDateTime.now();
        User user = new User();
        user.setId(1l);
        user.setUsername("an");
        user.setRole("Admin");
        user.setCreatedAt(dateTime);
        user.setUuid(strUuid);
        when(repository.findByUuid(strUuid)).thenReturn(Optional.of(user));

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(user);
        userRoleEntity.setRole(role);
        when(userRoleRepository.save(userRoleEntity)).thenReturn(userRoleEntity);
        userManagementService.saveUserRole(user.getUuid(), role);

    }

    @Test
    public void findAllAccess() {
        LocalDateTime dateTime = LocalDateTime.now();

        //Actual
        Pageable sizePagination = PageRequest.of(0, 5);
        User user = new User();
        user.setId(1l);
        user.setUsername("an");
        user.setMaximumAccessUser(35);
        user.setUpdateAccessUser(dateTime);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        Page<User> userPageActual = new PageImpl<>(userList, sizePagination, userList.size());
        PageResDto<AllAccessUsersDto> pageResDtoActual = pageUtils.convertPageEntityToDTO(userPageActual, AllAccessUsersDto.class);

        //Expected
        ContentPageDto contentPageDto = new ContentPageDto(1,1,5,0);
        AllAccessUsersDto userManagementDto = new AllAccessUsersDto(1l, "an", 35, dateTime);
        List<AllAccessUsersDto> userManagementDtos = new ArrayList<>();
        userManagementDtos.add(userManagementDto);
        PageResDto<AllAccessUsersDto> pageResDto = new PageResDto<>(userManagementDtos, contentPageDto);

        when(repository.findAllAccess(sizePagination)).thenReturn(userPageActual);
        when(pageUtilsMock.convertPageEntityToDTO(userPageActual, AllAccessUsersDto.class)).thenReturn(pageResDtoActual);
        userManagementService.findAllAccess(sizePagination);


        assertEquals(pageResDto, pageResDtoActual);
    }

    @Test
    public void addAccessUser() {
        LocalDateTime dateTime = LocalDateTime.now();
        int maximum = 35;
        Long id = 1l;

        //Actual
        User user = new User();
        user.setId(1l);
        user.setMaximumAccessUser(0);
        user.setUsername("Andong");
        when(repository.findById(id)).thenReturn(Optional.of(user));

        user.setMaximumAccessUser(35);
        user.setUpdateAccessUser(dateTime);
        when(repository.save(user)).thenReturn(user);
        userManagementService.addAccessUser(maximum, id);
    }

    @Test(expected = UserNotFoundException.class)
    public void addAccessUserNotFound() {
        int maximum = 35;
        Long id = 122l;
        //Actual
        User user = new User();
        user.setId(122l);
        user.setMaximumAccessUser(0);
        user.setUsername("Andong");
        when(repository.findById(user.getId())).thenReturn(Optional.empty());
        userManagementService.addAccessUser(maximum, id);
    }
}
