package com.mac.martial_arts_cms.controller;

import com.mac.martial_arts_cms.model.dto.DojoDto;
import com.mac.martial_arts_cms.model.dto.PageResDto;
import com.mac.martial_arts_cms.service.inf.DojoService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class DojoController.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RestController
@RequestMapping( "/dojo")
public class DojoController extends BaseController {

    private DojoService dojoService;

    @Autowired
    public DojoController(DojoService dojoService) {
        this.dojoService = dojoService;
    }

    /**
     * getAll
     * @param page
     * @param size
     * @return PageResDto<DojoDto>
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable sizePagination = PageRequest.of(page, size);
        PageResDto<DojoDto> list = dojoService.findAll(sizePagination);
        return success(CommonConstants.MessageSuccess.SC009, list, null);
    }
}
