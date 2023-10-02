package com.mac.m2land_cms.controller;

import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.request.SuspensionRequest;
import com.mac.m2land_cms.model.response.GameLogResponse;
import com.mac.m2land_cms.model.response.OperationLogResponse;
import com.mac.m2land_cms.model.response.UserAccountResponse;
import com.mac.m2land_cms.model.response.UserResponse;
import com.mac.m2land_cms.repository.UserSpecifications;
import com.mac.m2land_cms.service.inf.GameLogService;
import com.mac.m2land_cms.service.inf.OperationLogService;
import com.mac.m2land_cms.service.inf.UserService;
import com.mac.m2land_cms.utils.CommonConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class UserController.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RestController
@RequestMapping( "/users")
public class UserController extends BaseController{

    private ModelMapper modelMapper;
    private UserService userService;
    private GameLogService gameLogService;
    private OperationLogService operationLogService;
    private UserSpecifications userSpecifications;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService, GameLogService gameLogService,
                          OperationLogService operationLogService,UserSpecifications userSpecifications) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.gameLogService = gameLogService;
        this.operationLogService = operationLogService;
        this.userSpecifications = userSpecifications;
    }


    /**
     *
     * @param searchCondition
     * @param searchWord
     * @return ResponseEntity
     */
    @GetMapping(value = "/searchAccountUser")
    public ResponseEntity<?> searchAccountUser(@RequestParam("searchCondition") String searchCondition,
                                               @RequestParam("searchWord") String searchWord,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size){

        Pageable sizePagination = PageRequest.of(page, size);
        Page<User> response =
                userSpecifications.searchUserAccount(searchCondition.toLowerCase(),
                        searchWord.toLowerCase(),sizePagination);

        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }

    /**
     *
     * @param username
     * @return ResponseEntity
     */
    @PutMapping(value = "/releaseSuspension")
    public ResponseEntity<?> releaseSuspension(@RequestParam("username") String username){
        userService.releaseSuspension(username);
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }

    /**
     *
     * @param request
     * @return ResponseEntity
     */
    @PutMapping(value = "/suspension")
    public ResponseEntity<?> suspension(@RequestBody SuspensionRequest request){
        userService.UpdateSuspension(
                request.getId(),
                request.getSuspensionStart(),
                request.getSuspensionEnd(),
                request.getReasonSuspension()
        );
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }


    /**
     *
     * @param username
     * @return ResponseEntity
     */
    @GetMapping(value = "/getUserAccountInfo")
    public ResponseEntity<?> getUserAccountInfo(@RequestParam("username") String username){
        UserAccountResponse response = userService.getUserAccountInfo(username);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }


    /**
     *
     * @param userId
     * @return ResponseEntity
     */
    @GetMapping(value = "/gameLog")
    public ResponseEntity<?> getGameLogByUserId(@Param("userId") Long userId,
                                                @RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        Pageable sizePagination = PageRequest.of(page, size);
        PageResDto<GameLogResponse> response =
                gameLogService.findByUserId(userId, sizePagination);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }


    /**
     *
     * @param userId
     * @return ResponseEntity
     */
    @GetMapping(value = "/operationLog")
    public ResponseEntity<?> getOperationLogByUserId(@Param("userId") Long userId,
                                                     @RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        Pageable sizePagination = PageRequest.of(page, size);
        PageResDto<OperationLogResponse> response =
                operationLogService.findByUserId(sizePagination,userId);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }

}
