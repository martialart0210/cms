package com.mac.martial_arts_cms.controller;

import com.mac.martial_arts_cms.model.response.FigureResponse;
import com.mac.martial_arts_cms.service.inf.FigureService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class FigureController.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RestController
@RequestMapping(value = "/figure")
public class FigureController extends BaseController{

    @Autowired
    private FigureService figureService;

    /**
     *
     * @param username
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> figureInfo(@RequestParam("username") String username){
        FigureResponse response = figureService.getFigureInfo(username);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }
}
