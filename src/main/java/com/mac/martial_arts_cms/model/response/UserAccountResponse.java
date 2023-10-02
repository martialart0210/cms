package com.mac.martial_arts_cms.model.response;

import com.mac.martial_arts_cms.model.enum_class.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class UserAccountResponse.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountResponse {

    private Long id;

    private boolean connectionStatus;

    private String createdAt;

    private String sysCreateCharacter;

    private Status accountType;

    private String uuid;
}
