package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.response.OperationLogResponse;

import java.util.List;

/**
 * The interface OperationLogService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface OperationLogService {

    List<OperationLogResponse> findByUserId(Long userId);
}
