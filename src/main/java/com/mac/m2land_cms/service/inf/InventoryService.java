package com.mac.m2land_cms.service.inf;

import com.mac.m2land_cms.model.response.InventoryResponse;

import java.util.List;

/**
 * The Class InventoryService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface InventoryService {

    List<InventoryResponse> getInventoryByUserId(Long idUser);
}
