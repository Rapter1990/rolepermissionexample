package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

/**
 * Exception class named {@link PermissionNotFoundException} thrown when the specified permission is not found.
 */
public class PermissionNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6226206922525682121L;

    private static final String DEFAULT_MESSAGE = """
            Permission not found!
            """;

    /**
     * Constructs a new {@link PermissionNotFoundException} with the default message.
     */
    public PermissionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new {@link PermissionNotFoundException} with the default message and an additional message.
     *
     * @param message the additional message to include.
     */
    public PermissionNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
