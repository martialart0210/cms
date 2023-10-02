package com.mac.m2land_cms.controller;

import com.mac.m2land_cms.model.dto.*;
import com.mac.m2land_cms.model.request.AccessUserRequest;
import com.mac.m2land_cms.service.inf.UserManagementService;
import com.mac.m2land_cms.utils.CommonConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

/**
 * The Class UserManagementController.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RestController
@RequestMapping(value = "/userManagement")
public class UserManagementController extends BaseController {

    private UserManagementService userManagementService;

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    /**
     *
     * @param page
     * @param size
     * @return PageResDto<AllUserManagementDto>
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getRole(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable sizePagination = PageRequest.of(page, size);
        PageResDto<AllUserManagementDto> list = userManagementService.findAll(sizePagination);
        return success(CommonConstants.MessageSuccess.SC009, list, null);
    }

    /**
     * addUser
     * @param user
     * @return response success
     */
    @PostMapping(value = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserCreateDto user) throws RoleNotFoundException {
        userManagementService.addUser(user);
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }

    /**
     * updateUser
     * @param user
     * @return response success
     */
    @PutMapping(value = "/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserEditDto user) throws RoleNotFoundException {
        userManagementService.updateUser(user);
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }

    /**
     * deleteUser
     * @param id
     * @return response success
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userManagementService.deleteUser(id);
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }

    /**
     * getSuspension
     * @return response success
     */
    @GetMapping(value = "/get-suspension")
    public ResponseEntity<?> getSuspension(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable sizePagination = PageRequest.of(page, size);
        PageResDto<UserSuspensionDto> list = userManagementService.findAllSuspension(sizePagination);
        return success(CommonConstants.MessageSuccess.SC009, list, null);
    }

    /**
     * getAllAccess
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/getAllAccess")
    @Operation(summary = "list-access-users")
    public ResponseEntity<?> getAllAccess(@RequestParam("page") int page, @RequestParam("size") int size) {
        Sort sort = Sort.by("UPDATE_ACCESS_USER").descending();
        Pageable sizePagination = PageRequest.of(page, size, sort);
        PageResDto<AllAccessUsersDto> pageResDto = userManagementService.findAllAccess(sizePagination);
        return success(CommonConstants.MessageSuccess.SC009, pageResDto, null);
    }

    /**
     * addAccess
     * @return ResponseEntity
     */
    @PostMapping(value = "/addAccess")
    @Operation(summary = "registration-access-user")
    public ResponseEntity<?> addAccess(@RequestBody AccessUserRequest request) {
        userManagementService.addAccessUser(request.getMaximum(), request.getId());
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }

}
