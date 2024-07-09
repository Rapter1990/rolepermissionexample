package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link RoleNotFoundException} thrown when the specified role is not found.
 */
public class RoleNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6226206922525682121L;

    private static final String DEFAULT_MESSAGE = """
            Role not found!
            """;

    /**
     * Constructs a new {@link RoleNotFoundException} with the default message.
     */
    public RoleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link RoleNotFoundException} with the default message and an additional message.
     *
     * @param message the additional message to include.
     */
    public RoleNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}