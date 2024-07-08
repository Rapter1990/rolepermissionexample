package com.security.rolepermissionexample.auth.service;

import com.security.rolepermissionexample.auth.model.dto.request.TokenInvalidateRequest;

public interface LogoutService {
    void logout(final TokenInvalidateRequest tokenInvalidateRequest);

}
