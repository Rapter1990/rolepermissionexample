package com.security.rolepermissionexample.auth.controller;

import com.security.rolepermissionexample.auth.model.Permission;
import com.security.rolepermissionexample.auth.model.Role;
import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.User;
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
import com.security.rolepermissionexample.base.AbstractRestControllerTest;
import com.security.rolepermissionexample.common.model.dto.response.CustomResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest extends AbstractRestControllerTest {

    @MockBean
    private RegisterService registerService;

    @MockBean
    private LoginService loginService;

    @MockBean
    private RefreshTokenService refreshTokenService;

    @MockBean
    private LogoutService logoutService;

    private final TokenToTokenResponseMapper tokenToTokenResponseMapper = TokenToTokenResponseMapper.initialize();


    @Test
    void givenValidAdminRegisterRequestWithAdminCreate_whenRegisterAdmin_thenSuccess() throws Exception {

        // Given
        final RegisterRequest request = RegisterRequest.builder()
                .email("admincreate@example.com")
                .password("password123")
                .firstName("admin firstName")
                .lastName("admin lastName")
                .phoneNumber("123456789010")
                .role(List.of("ADMIN"))
                .permissions(List.of("admin:create"))
                .build();

        Permission adminCreatePermission = Permission.builder()
                .id(UUID.randomUUID().toString())
                .name("admin:create")
                .build();

        Role adminRole = Role.builder()
                .id(UUID.randomUUID().toString())
                .name("ADMIN")
                .permissions(List.of(adminCreatePermission))
                .build();

        User mockAdminWithCreate = User.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .roles(List.of(adminRole))
                .permissions(List.of(adminCreatePermission))
                .build();

        // When
        when(registerService.registerUser(any(RegisterRequest.class))).thenReturn(mockAdminWithCreate);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(CustomResponse.SUCCESS)));

        // Verify
        verify(registerService, times(1)).registerUser(any(RegisterRequest.class));

    }

    @Test
    void givenLoginRequestWithAdminCreate_WhenLoginForAdmin_ThenReturnToken() throws Exception {

        // Given
        LoginRequest loginRequest = LoginRequest.builder()
                .email("admin@example.com")
                .password("password")
                .build();

        Token mockToken = Token.builder()
                .accessToken("mockAccessToken")
                .accessTokenExpiresAt(3600L)
                .refreshToken("mockRefreshToken")
                .build();

        TokenResponse expectedTokenResponse = tokenToTokenResponseMapper.map(mockToken);

        // When
        when(loginService.login(any(LoginRequest.class))).thenReturn(mockToken);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.accessToken").value(expectedTokenResponse.getAccessToken()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.accessTokenExpiresAt").value(expectedTokenResponse.getAccessTokenExpiresAt()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.refreshToken").value(expectedTokenResponse.getRefreshToken()));

        // Verify
        verify(loginService, times(1)).login(any(LoginRequest.class));

    }


    @Test
    void givenTokenRefreshRequestWithAdminCreate_WhenRefreshTokenForAdmin_ThenReturnTokenResponse() throws Exception {

        // Given
        TokenRefreshRequest tokenRefreshRequest = new TokenRefreshRequest("refreshToken");

        Token mockToken = Token.builder()
                .accessToken("mockAccessToken")
                .accessTokenExpiresAt(3600L)
                .refreshToken("mockRefreshToken")
                .build();

        TokenResponse expectedTokenResponse = tokenToTokenResponseMapper.map(mockToken);

        // When
        when(refreshTokenService.refreshToken(any(TokenRefreshRequest.class))).thenReturn(mockToken);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/refresh-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tokenRefreshRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccess").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.accessToken").value(expectedTokenResponse.getAccessToken()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.accessTokenExpiresAt").value(expectedTokenResponse.getAccessTokenExpiresAt()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.refreshToken").value(expectedTokenResponse.getRefreshToken()));

        // Verify
        verify(refreshTokenService, times(1)).refreshToken(any(TokenRefreshRequest.class));

    }


    @Test
    void givenTokenInvalidateRequestWithAdminCreate_WhenLogoutForAdmin_ThenReturnInvalidateToken() throws Exception {

        // Given
        TokenInvalidateRequest tokenInvalidateRequest = TokenInvalidateRequest.builder()
                .accessToken("Bearer " + mockAdminCreateToken.getAccessToken())
                .refreshToken(mockAdminCreateToken.getRefreshToken())
                .build();

        // When
        doNothing().when(logoutService).logout(any(TokenInvalidateRequest.class));

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tokenInvalidateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(CustomResponse.SUCCESS)));

        // Verify
        verify(logoutService, times(1)).logout(any(TokenInvalidateRequest.class));

    }

    @Test
    void givenValidAdminRegisterRequestWithAdminGet_whenRegisterAdmin_thenSuccess() throws Exception {

        // Given
        final RegisterRequest request = RegisterRequest.builder()
                .email("admincreate@example.com")
                .password("password123")
                .firstName("admin firstName")
                .lastName("admin lastName")
                .phoneNumber("123456789010")
                .role(List.of("ADMIN"))
                .permissions(List.of("admin:get"))
                .build();

        Permission adminGetPermission = Permission.builder()
                .id(UUID.randomUUID().toString())
                .name("admin:get")
                .build();

        Role adminRole = Role.builder()
                .id(UUID.randomUUID().toString())
                .name("ADMIN")
                .permissions(List.of(adminGetPermission))
                .build();

        User mockAdminWithGet = User.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .roles(List.of(adminRole))
                .permissions(List.of(adminGetPermission))
                .build();

        // When
        when(registerService.registerUser(any(RegisterRequest.class))).thenReturn(mockAdminWithGet);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(CustomResponse.SUCCESS)));

        // Verify
        verify(registerService, times(1)).registerUser(any(RegisterRequest.class));

    }

    @Test
    void givenValidAdminRegisterRequestWithAdminUpdate_whenRegisterAdmin_thenSuccess() throws Exception {

        // Given
        final RegisterRequest request = RegisterRequest.builder()
                .email("admincreate@example.com")
                .password("password123")
                .firstName("admin firstName")
                .lastName("admin lastName")
                .phoneNumber("123456789010")
                .role(List.of("ADMIN"))
                .permissions(List.of("admin:update"))
                .build();

        Permission adminUpdatePermission = Permission.builder()
                .id(UUID.randomUUID().toString())
                .name("admin:update")
                .build();

        Role adminRole = Role.builder()
                .id(UUID.randomUUID().toString())
                .name("ADMIN")
                .permissions(List.of(adminUpdatePermission))
                .build();

        User mockAdminWithUpdate = User.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .roles(List.of(adminRole))
                .permissions(List.of(adminUpdatePermission))
                .build();

        // When
        when(registerService.registerUser(any(RegisterRequest.class))).thenReturn(mockAdminWithUpdate);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(CustomResponse.SUCCESS)));

        // Verify
        verify(registerService, times(1)).registerUser(any(RegisterRequest.class));

    }

    @Test
    void givenValidAdminRegisterRequestWithAdminDelete_whenRegisterAdmin_thenSuccess() throws Exception {

        // Given
        final RegisterRequest request = RegisterRequest.builder()
                .email("admincreate@example.com")
                .password("password123")
                .firstName("admin firstName")
                .lastName("admin lastName")
                .phoneNumber("123456789010")
                .role(List.of("ADMIN"))
                .permissions(List.of("admin:delete"))
                .build();

        Permission adminDeletePermission = Permission.builder()
                .id(UUID.randomUUID().toString())
                .name("admin:delete")
                .build();

        Role adminRole = Role.builder()
                .id(UUID.randomUUID().toString())
                .name("ADMIN")
                .permissions(List.of(adminDeletePermission))
                .build();

        User mockAdminWithDelete = User.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .roles(List.of(adminRole))
                .permissions(List.of(adminDeletePermission))
                .build();

        // When
        when(registerService.registerUser(any(RegisterRequest.class))).thenReturn(mockAdminWithDelete);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(CustomResponse.SUCCESS)));

        // Verify
        verify(registerService, times(1)).registerUser(any(RegisterRequest.class));

    }

    @Test
    void givenValidAdminRegisterRequestWithUserGet_whenRegisterAdmin_thenSuccess() throws Exception {

        // Given
        final RegisterRequest request = RegisterRequest.builder()
                .email("admincreate@example.com")
                .password("password123")
                .firstName("admin firstName")
                .lastName("admin lastName")
                .phoneNumber("123456789010")
                .role(List.of("USER"))
                .permissions(List.of("user:get"))
                .build();

        Permission userGetPermission = Permission.builder()
                .id(UUID.randomUUID().toString())
                .name("user:get")
                .build();

        Role userRole = Role.builder()
                .id(UUID.randomUUID().toString())
                .name("USER")
                .permissions(List.of(userGetPermission))
                .build();

        User mockUserWithCreate = User.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .roles(List.of(userRole))
                .permissions(List.of(userGetPermission))
                .build();

        // When
        when(registerService.registerUser(any(RegisterRequest.class))).thenReturn(mockUserWithCreate);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authentication/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(CustomResponse.SUCCESS)));

        // Verify
        verify(registerService, times(1)).registerUser(any(RegisterRequest.class));

    }

}