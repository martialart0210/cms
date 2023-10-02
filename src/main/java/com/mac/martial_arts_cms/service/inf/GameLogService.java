package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.response.GameLogResponse;

import java.util.List;

/**
 * The interface GameLogService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface GameLogService {

    List<GameLogResponse> findByUserId(Long userId);
}
