package com.mac.m2land_cms.controller;

import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.dto.VideoDto;
import com.mac.m2land_cms.service.inf.VideoService;
import com.mac.m2land_cms.utils.CommonConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The Class VideoController.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RestController
@RequestMapping( "/video")
@Tag(name = "scrap-management-controller ")
public class VideoController extends BaseController {

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * getAll
     * @param page
     * @param size
     * @return PageResDto<VideoDto>
     */
    @GetMapping(value = "/getAll")
    @Operation(summary = "list-video")
    public ResponseEntity<?> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable sizePagination = PageRequest.of(page, size);
        PageResDto<VideoDto> list = videoService.findAll(sizePagination);
        return success(CommonConstants.MessageSuccess.SC009, list, null);
    }

    /**
     * addVideo
     * @param link
     * @return response success
     */
    @PostMapping(value = "/add")
    @Operation(summary = "register-video")
    public ResponseEntity<?> addVideo(@RequestParam("link") String link) {
        videoService.addVideo(link);
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }

    /**
     * deleteVideo
     * @param id
     * @return response success
     */
    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "delete-video")
    public ResponseEntity<?> deleteVideo(@PathVariable("id") Long id) {
        videoService.deleteVideo(id);
        return success(CommonConstants.MessageSuccess.SC009, null, null);
    }
}
