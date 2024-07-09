package com.security.rolepermissionexample.auth.model.dto.response;

import lombok.*;

/**
 * Represents a response as {@link TokenResponse} containing access and refresh tokens.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private Long accessTokenExpiresAt;
    private String refreshToken;

}
