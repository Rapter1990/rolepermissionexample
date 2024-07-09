package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link UserAlreadyExistException} thrown when a user already exists.
 */
public class UserAlreadyExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3898725915862534787L;

    private static final String DEFAULT_MESSAGE = """
            User already exist!
            """;

    /**
     * Constructs a new {@link UserAlreadyExistException} with the default message.
     */
    public UserAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link UserAlreadyExistException} with the default message and an additional message.
     *
     * @param message the additional message to include.
     */
    public UserAlreadyExistException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
