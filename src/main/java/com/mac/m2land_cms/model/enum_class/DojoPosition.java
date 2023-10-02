package com.mac.m2land_cms.model.enum_class;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * The enum DojoPosition.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */

public enum DojoPosition {
    @JsonProperty("INSTRUCTOR")
    INSTRUCTOR(0),
    @JsonProperty("SUB_INSTRUCTOR")
    SUB_INSTRUCTOR(1),
    @JsonProperty("TRAINEE")
    TRAINEE(2),
    ;

    private static Map<Integer, DojoPosition> lookup;
    private Integer value;

    DojoPosition(Integer value) {
        this.value = value;
    }

    static {
        try {
            DojoPosition[] vals = DojoPosition.values();
            lookup = new HashMap<Integer, DojoPosition>(vals.length);

            for (DojoPosition rpt : vals)
                lookup.put(rpt.getValue(), rpt);
        } catch (Exception ignored) {

        }
    }

    public static DojoPosition fromValue(Integer value) {
        return lookup.get(value);
    }

    public static Integer getStatusEnum(String val) {
        return switch (val) {
            case "INSTRUCTOR" -> 0;
            case "SUB_INSTRUCTOR" -> 1;
            case "TRAINEE" -> 2;
            default -> null;
        };
    }

    public Integer getValue() {
        return this.value;
    }
}
