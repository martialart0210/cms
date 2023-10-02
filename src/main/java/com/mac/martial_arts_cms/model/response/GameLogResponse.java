package com.mac.martial_arts_cms.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class GameLogResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameLogResponse {

    private String label;

    private String timestamp;

    private String detail;
}
