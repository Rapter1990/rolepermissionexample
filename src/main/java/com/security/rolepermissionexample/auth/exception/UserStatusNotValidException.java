package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link UserStatusNotValidException} thrown when the user status is not valid.
 */
public class UserStatusNotValidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3793140292762351950L;

    private static final String DEFAULT_MESSAGE = """
            User status is not valid!
            """;

    /**
     * Constructs a new {@link UserStatusNotValidException} with the default message.
     */
    public UserStatusNotValidException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link UserStatusNotValidException} with the default message and an additional message.
     *
     * @param message the additional message to include.
     */
    public UserStatusNotValidException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
