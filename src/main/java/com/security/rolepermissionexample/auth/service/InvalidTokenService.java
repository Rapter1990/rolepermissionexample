package com.security.rolepermissionexample.auth.service;

import java.util.Set;

/**
 * Service interface named {@link InvalidTokenService} for managing invalid tokens.
 */
public interface InvalidTokenService {

    /**
     * Invalidates multiple tokens identified by their IDs.
     *
     * @param tokenIds The set of token IDs to invalidate.
     */
    void invalidateTokens(final Set<String> tokenIds);

    /**
     * Checks if a token identified by its ID is invalid.
     *
     * @param tokenId The ID of the token to check for invalidity.
     */
    void checkForInvalidityOfToken(final String tokenId);

}
