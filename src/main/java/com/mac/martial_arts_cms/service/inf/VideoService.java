package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.dto.PageResDto;
import com.mac.martial_arts_cms.model.dto.VideoDto;
import org.springframework.data.domain.Pageable;

/**
 * The interface VideoService.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
public interface VideoService {
    /**
     * findAll
     * @param pageable
     * @return PageResDto<VideoDto>
     */
    PageResDto<VideoDto> findAll(Pageable pageable);

    /**
     * addVideo
     * @param link video
     */
    void addVideo(String link);

    /**
     * deleteVideo
     * @param id
     */
    void deleteVideo(Long id);
}
