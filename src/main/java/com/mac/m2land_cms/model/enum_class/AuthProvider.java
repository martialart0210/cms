package com.mac.m2land_cms.model.enum_class;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


/**
 * The enum AuthProvider.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Getter
public enum AuthProvider {
    @JsonProperty("local")
    local(0),
    @JsonProperty("facebook")
    facebook(1),
    @JsonProperty("google")
    google(2),
    @JsonProperty("github")
    github(3),
    @JsonProperty("kakao")
    kakao(4),
    @JsonProperty("naver")
    naver(5),
    @JsonProperty("apple")
    apple(6);


    private static Map<Integer, AuthProvider> lookup;
    private Integer value;

    AuthProvider(Integer value) {
        this.value = value;
    }


    static {
        try {
            AuthProvider[] vals = AuthProvider.values();
            lookup = new HashMap<Integer, AuthProvider>(vals.length);

            for (AuthProvider rpt : vals)
                lookup.put(rpt.getValue(), rpt);
        } catch (Exception ignored) {

        }
    }


    public static AuthProvider fromValue(Integer value) {
        return lookup.get(value);
    }

    public static Integer getStatusEnum(String val) {
        return switch (val) {
            case "LOCAL" -> 0;
            case "FACEBOOK" -> 1;
            case "GOOGLE" -> 2;
            case "GITHUB" -> 3;
            case "KAKAO" -> 4;
            case "NAVER" -> 5;
            case "APPLE" -> 6;
            default -> null;
        };
    }
}
