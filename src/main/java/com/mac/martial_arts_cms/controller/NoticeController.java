package com.mac.martial_arts_cms.controller;

import com.mac.martial_arts_cms.model.request.NoticeRegisterRequest;
import com.mac.martial_arts_cms.model.response.NoticeRegisterResponse;
import com.mac.martial_arts_cms.service.inf.NoticeService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class NoticeController.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RestController
@RequestMapping(value = "/notices")
public class NoticeController extends BaseController{

    @Autowired
    private NoticeService noticeService;


    /**
     *
     * @param request
     * @return ResponseEntity<?>
     */
    @Hidden
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerNotice(@RequestBody NoticeRegisterRequest request){
        NoticeRegisterResponse response = noticeService.register(request);
        return success(CommonConstants.MessageSuccess.SC009, response, null);
    }
}
