package com.security.rolepermissionexample.auth.service;

import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.dto.request.TokenRefreshRequest;

public interface RefreshTokenService {
    Token refreshToken(final TokenRefreshRequest tokenRefreshRequest);

}
