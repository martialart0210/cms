package com.mac.m2land_cms.service.inf;

import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.response.GameLogResponse;
import org.springframework.data.domain.Pageable;

/**
 * The interface GameLogService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface GameLogService {

    PageResDto<GameLogResponse> findByUserId(Long userId, Pageable pageable);
}
