package com.mac.martial_arts_cms.service.inf;

import com.mac.martial_arts_cms.model.response.InventoryResponse;

import java.util.List;

/**
 * The Class InventoryService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface InventoryService {

    List<InventoryResponse> getInventoryByUserId(Long idUser);
}
