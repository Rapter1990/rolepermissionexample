package com.security.rolepermissionexample.auth.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * Represents a token domain object as {@link Token} used in the application.
 */
@Getter
@Builder
public class Token {

    private String accessToken;
    private Long accessTokenExpiresAt;
    private String refreshToken;

    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Checks if the provided authorization header is a bearer token.
     *
     * @param authorizationHeader The authorization header string.
     * @return {@code true} if the header is a bearer token; otherwise {@code false}.
     */
    public static boolean isBearerToken(final String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader) &&
                authorizationHeader.startsWith(TOKEN_PREFIX);
    }

    /**
     * Extracts the JWT token from the authorization header.
     *
     * @param authorizationHeader The authorization header string.
     * @return The JWT token string extracted from the header.
     */
    public static String getJwt(final String authorizationHeader) {
        return authorizationHeader.replace(TOKEN_PREFIX, "");
    }

}
