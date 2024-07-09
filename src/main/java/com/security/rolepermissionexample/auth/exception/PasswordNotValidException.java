package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link PasswordNotValidException} thrown when the password is not valid.
 */
public class PasswordNotValidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5289337704108394302L;

    private static final String DEFAULT_MESSAGE = """
            Password is not valid!
            """;

    /**
     * Constructs a new {@link PasswordNotValidException} with the default message.
     */
    public PasswordNotValidException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link PasswordNotValidException} with the default message and an additional message.
     *
     * @param message the additional message to include.
     */
    public PasswordNotValidException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
