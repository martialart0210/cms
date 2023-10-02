package com.mac.m2land_cms.controller;

import com.mac.m2land_cms.model.request.UserCharacterRequest;
import com.mac.m2land_cms.model.response.UserCharacterResponse;
import com.mac.m2land_cms.service.inf.UserCharacterService;
import com.mac.m2land_cms.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class UserCharacterController.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RestController
@RequestMapping(value = "/userCharacter")
public class UserCharacterController extends BaseController{

    private UserCharacterService userCharacterService;

    @Autowired
    public UserCharacterController(UserCharacterService userCharacterService) {
        this.userCharacterService = userCharacterService;
    }

    /**
     *
     * @param userId
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> userCharacterInfo(@RequestParam("userId") Long userId){
        UserCharacterResponse response = userCharacterService.getUserCharacterInfo(userId);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }


    /**
     *
     * @param request
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity<?> updateFigure(@RequestBody UserCharacterRequest request){
        UserCharacterResponse response = userCharacterService.updateUserCharacter(request);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }
}
