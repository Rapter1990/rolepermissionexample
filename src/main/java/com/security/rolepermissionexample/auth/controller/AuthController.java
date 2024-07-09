package com.security.rolepermissionexample.auth.controller;

import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.dto.request.LoginRequest;
import com.security.rolepermissionexample.auth.model.dto.request.RegisterRequest;
import com.security.rolepermissionexample.auth.model.dto.request.TokenInvalidateRequest;
import com.security.rolepermissionexample.auth.model.dto.request.TokenRefreshRequest;
import com.security.rolepermissionexample.auth.model.dto.response.TokenResponse;
import com.security.rolepermissionexample.auth.model.mapper.TokenToTokenResponseMapper;
import com.security.rolepermissionexample.auth.service.LoginService;
import com.security.rolepermissionexample.auth.service.LogoutService;
import com.security.rolepermissionexample.auth.service.RefreshTokenService;
import com.security.rolepermissionexample.auth.service.RegisterService;
import com.security.rolepermissionexample.common.model.dto.response.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller named {@link AuthController}
 * for handling authentication-related requests such as registration, login, token refresh, and logout.
 */
@RestController
@RequestMapping("/api/v1/authentication/user")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterService registerService;

    private final LoginService loginService;

    private final RefreshTokenService refreshTokenService;

    private final LogoutService logoutService;


    private final TokenToTokenResponseMapper tokenToTokenResponseMapper = TokenToTokenResponseMapper.initialize();

    /**
     * Registers a new user.
     *
     * @param registerRequest the request object containing registration details.
     * @return a {@link CustomResponse} indicating success or failure.
     */
    @PostMapping("/register")
    public CustomResponse<Void> registerAdmin(@RequestBody @Valid final RegisterRequest registerRequest) {
        registerService.registerUser(registerRequest);
        return CustomResponse.SUCCESS;
    }

    /**
     * Logs in a user and returns an access token.
     *
     * @param loginRequest the request object containing login credentials.
     * @return a {@link CustomResponse} containing the {@link TokenResponse}.
     */
    @PostMapping("/login")
    public CustomResponse<TokenResponse> loginAdmin(@RequestBody @Valid final LoginRequest loginRequest) {
        final Token token = loginService.login(loginRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return CustomResponse.successOf(tokenResponse);
    }

    /**
     * Refreshes the access token using a refresh token.
     *
     * @param tokenRefreshRequest the request object containing the refresh token.
     * @return a {@link CustomResponse} containing the new {@link TokenResponse}.
     */
    @PostMapping("/refresh-token")
    public CustomResponse<TokenResponse> refreshToken(@RequestBody @Valid final TokenRefreshRequest tokenRefreshRequest) {
        final Token token = refreshTokenService.refreshToken(tokenRefreshRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return CustomResponse.successOf(tokenResponse);
    }

    /**
     * Logs out a user by invalidating their token.
     *
     * @param tokenInvalidateRequest the request object containing the token to be invalidated.
     * @return a {@link CustomResponse} indicating success or failure.
     */
    @PostMapping("/logout")
    public CustomResponse<Void> logout(@RequestBody @Valid final TokenInvalidateRequest tokenInvalidateRequest) {
        logoutService.logout(tokenInvalidateRequest);
        return CustomResponse.SUCCESS;
    }

}
