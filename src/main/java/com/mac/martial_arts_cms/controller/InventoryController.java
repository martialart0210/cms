package com.mac.martial_arts_cms.controller;

import com.mac.martial_arts_cms.model.response.InventoryResponse;
import com.mac.martial_arts_cms.service.inf.InventoryService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Class InventoryController.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RestController
@RequestMapping(value = "/inventory")
public class InventoryController extends BaseController{


    @Autowired
    private InventoryService itemService;


    /**
     *
     * @param idUser
     * @return ResponseEntity<?>
     */
    @GetMapping(value = "/getInventoryByUserId")
    private ResponseEntity<?> getInventoryByUserId(@RequestParam("idUser") Long idUser){

        List<InventoryResponse> responses = itemService.getInventoryByUserId(idUser);
        return success(CommonConstants.MessageSuccess.SC009, responses, null);
    }
}
