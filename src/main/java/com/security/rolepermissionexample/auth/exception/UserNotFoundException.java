package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link UserNotFoundException } thrown when the specified user is not found.
 */
public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6226206922525682121L;

    private static final String DEFAULT_MESSAGE = """
            User not found!
            """;

    /**
     * Constructs a new {@link UserNotFoundException} with the default message.
     */
    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link UserNotFoundException} with the default message and an additional message.
     *
     * @param message the additional message to include.
     */
    public UserNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
