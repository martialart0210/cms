package com.mac.martial_arts_cms.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class InventoryResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryResponse {

    private Long id;

    private String name;

    private Long count;
}
