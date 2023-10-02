package com.mac.martial_arts_cms.controller;

import com.mac.martial_arts_cms.model.dto.AllUserManagementDto;
import com.mac.martial_arts_cms.model.dto.PageResDto;
import com.mac.martial_arts_cms.model.dto.UserCreateDto;
import com.mac.martial_arts_cms.model.dto.UserSuspensionDto;
import com.mac.martial_arts_cms.service.inf.UserManagementService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addUser(@RequestBody UserCreateDto user) {
        userManagementService.addUser(user);
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }

    /**
     * updateUser
     * @param user
     * @return response success
     */
    @PutMapping(value = "/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserCreateDto user)  {
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
}
