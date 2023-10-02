package com.mac.martial_arts_cms.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class FigureResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FigureResponse {
    private String characterName;
    private Long gold;
    private String guildName;
}
