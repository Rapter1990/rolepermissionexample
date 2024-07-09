package com.security.rolepermissionexample.auth.service;

import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.dto.request.TokenRefreshRequest;

/**
 * Service interface named {@link RefreshTokenService} for refreshing authentication tokens.
 */
public interface RefreshTokenService {

    /**
     * Refreshes an authentication token based on the provided refresh token request.
     *
     * @param tokenRefreshRequest The request containing the refresh token.
     * @return The refreshed authentication token.
     */
    Token refreshToken(final TokenRefreshRequest tokenRefreshRequest);

}
