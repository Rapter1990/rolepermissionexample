package com.security.rolepermissionexample.auth.service.impl;

import com.security.rolepermissionexample.auth.exception.UserNotFoundException;
import com.security.rolepermissionexample.auth.exception.UserStatusNotValidException;
import com.security.rolepermissionexample.auth.model.Token;
import com.security.rolepermissionexample.auth.model.dto.request.TokenRefreshRequest;
import com.security.rolepermissionexample.auth.model.entity.UserEntity;
import com.security.rolepermissionexample.auth.model.enums.TokenClaims;
import com.security.rolepermissionexample.auth.model.enums.UserStatus;
import com.security.rolepermissionexample.auth.repository.UserRepository;
import com.security.rolepermissionexample.auth.service.RefreshTokenService;
import com.security.rolepermissionexample.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public Token refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String adminId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final UserEntity userEntityFromDB = userRepository
                .findById(adminId)
                .orElseThrow(UserNotFoundException::new);

        this.validateAdminStatus(userEntityFromDB);

        return tokenService.generateToken(
                userEntityFromDB.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        );
    }

    private void validateAdminStatus(final UserEntity userEntity) {
        if (!(UserStatus.ACTIVE.equals(userEntity.getUserStatus()))) {
            throw new UserStatusNotValidException("UserStatus = " + userEntity.getUserStatus());
        }
    }

}
