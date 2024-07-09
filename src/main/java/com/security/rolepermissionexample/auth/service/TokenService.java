package com.security.rolepermissionexample.auth.service;

import com.security.rolepermissionexample.auth.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Map;
import java.util.Set;

/**
 * Service interface named {@link TokenService} for managing authentication tokens.
 */
public interface TokenService {

    /**
     * Generates an authentication token with specified claims.
     *
     * @param claims The claims to include in the token.
     * @return The generated authentication token.
     */
    Token generateToken(final Map<String, Object> claims);

    /**
     * Generates an authentication token with specified claims and a refresh token.
     *
     * @param claims        The claims to include in the token.
     * @param refreshToken  The refresh token associated with the authentication token.
     * @return The generated authentication token.
     */
    Token generateToken(final Map<String, Object> claims, final String refreshToken);

    /**
     * Retrieves authentication information from a token.
     *
     * @param token The authentication token.
     * @return Authentication details extracted from the token.
     */
    UsernamePasswordAuthenticationToken getAuthentication(final String token);

    /**
     * Verifies and validates a JWT token.
     *
     * @param jwt The JWT token to verify and validate.
     */
    void verifyAndValidate(final String jwt);

    /**
     * Verifies and validates multiple JWT tokens.
     *
     * @param jwts The set of JWT tokens to verify and validate.
     */
    void verifyAndValidate(final Set<String> jwts);

    /**
     * Retrieves the claims embedded within a JWT token.
     *
     * @param jwt The JWT token.
     * @return The claims extracted from the JWT token.
     */
    Jws<Claims> getClaims(final String jwt);

    /**
     * Retrieves the payload (claims) embedded within a JWT token.
     *
     * @param jwt The JWT token.
     * @return The payload (claims) extracted from the JWT token.
     */
    Claims getPayload(final String jwt);

    /**
     * Retrieves the ID (subject) from a JWT token.
     *
     * @param jwt The JWT token.
     * @return The ID (subject) extracted from the JWT token.
     */
    String getId(final String jwt);

}
