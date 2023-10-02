package com.mac.m2land_cms.service.inf;

/**
 * The Class TokenService.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public interface TokenService {

    void addToBlacklist(String token, long expiresInMinutes);

    boolean isBlacklisted(String token);

    void saveRefreshToken(String userId, String refreshToken, long expiresInMinutes);

    String getRefreshToken(String userId);

    void deleteRefreshToken(String username);

    void addSessionId(String session, String sessionId);

    String getSessionId(String userId);

}
