package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.response.FigureResponse;

/**
 * The Class FigureService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface FigureService {

    /**
     *
     * @param username
     * @return FigureResponse
     */
    FigureResponse getFigureInfo(String username);
}
