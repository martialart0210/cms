package com.mac.m2land_cms.service.inf;

import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.response.OperationLogResponse;
import org.springframework.data.domain.Pageable;

/**
 * The interface OperationLogService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface OperationLogService {

    PageResDto<OperationLogResponse> findByUserId(Pageable pageable, Long userId);
}
