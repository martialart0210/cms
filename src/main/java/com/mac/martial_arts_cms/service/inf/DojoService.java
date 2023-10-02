package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.dto.DojoDto;
import com.mac.martial_arts_cms.model.dto.PageResDto;
import org.springframework.data.domain.Pageable;
/**
 * The interface DojoService.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
public interface DojoService {

    /**
     * findAll
     * @param pageable
     * @return PageResDto<DojoDto>
     */
    PageResDto<DojoDto> findAll(Pageable pageable);
}
