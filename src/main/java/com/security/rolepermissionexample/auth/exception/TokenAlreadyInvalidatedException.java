package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link TokenAlreadyInvalidatedException} thrown when the token is already invalidated.
 */
public class TokenAlreadyInvalidatedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6924357230755962371L;

    private static final String DEFAULT_MESSAGE = """
            Token is already invalidated!
            """;

    /**
     * Constructs a new {@link TokenAlreadyInvalidatedException} with the default message.
     */
    public TokenAlreadyInvalidatedException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link TokenAlreadyInvalidatedException} with the default message and token ID.
     *
     * @param tokenId the token ID that is already invalidated.
     */
    public TokenAlreadyInvalidatedException(final String tokenId) {
        super(DEFAULT_MESSAGE + " TokenID = " + tokenId);
    }

}
