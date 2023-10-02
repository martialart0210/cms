package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.request.NoticeRegisterRequest;
import com.mac.martial_arts_cms.model.response.NoticeRegisterResponse;

/**
 * The interface NoticeService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface NoticeService {

    /**
     *
     * @param notice
     * @return NoticeRegisterRequest
     */
    NoticeRegisterResponse register(NoticeRegisterRequest notice);
}
