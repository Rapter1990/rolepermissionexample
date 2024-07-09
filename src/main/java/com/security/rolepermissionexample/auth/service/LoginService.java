package com.security.rolepermissionexample.auth.service;

import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.dto.request.LoginRequest;

/**
 * Service interface named {@link LoginService} for handling user login operations.
 */
public interface LoginService {

    /**
     * Performs user login based on the provided login request.
     *
     * @param loginRequest The login request containing user credentials.
     * @return The token representing the user's session.
     */
    Token login(final LoginRequest loginRequest);
}
