package com.mac.martial_arts_cms.controller;

import com.mac.martial_arts_cms.model.request.SuspensionRequest;
import com.mac.martial_arts_cms.model.response.GameLogResponse;
import com.mac.martial_arts_cms.model.response.OperationLogResponse;
import com.mac.martial_arts_cms.model.response.UserAccountResponse;
import com.mac.martial_arts_cms.model.response.UserResponse;
import com.mac.martial_arts_cms.service.inf.GameLogService;
import com.mac.martial_arts_cms.service.inf.OperationLogService;
import com.mac.martial_arts_cms.service.inf.UserService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import io.swagger.v3.oas.annotations.Hidden;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The Class UserController.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RestController
@RequestMapping( "/users")
public class UserController extends BaseController{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private GameLogService gameLogService;

    @Autowired
    private OperationLogService operationLogService;


    /**
     *
     * @param searchCondition
     * @param searchWord
     * @return ResponseEntity
     */
    @GetMapping(value = "/searchAccountUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchAccountUser(@RequestParam("searchCondition") String searchCondition,
                                               @RequestParam("searchWord") String searchWord){

        UserResponse response = userService.searchUserAccount(searchCondition.toLowerCase(),searchWord.toLowerCase());

        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }

    /**
     *
     * @param username
     * @return ResponseEntity
     */
    @Hidden
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
    @Hidden
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
    public ResponseEntity<?> getGameLogByUserId(@Param("userId") Long userId) {
        List<GameLogResponse> response = gameLogService.findByUserId(userId);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }


    /**
     *
     * @param userId
     * @return ResponseEntity
     */
    @GetMapping(value = "/operationLog")
    public ResponseEntity<?> getOperationLogByUserId(@Param("userId") Long userId) {
        List<OperationLogResponse> response = operationLogService.findByUserId(userId);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }

}
