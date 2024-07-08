package com.security.rolepermissionexample.auth.exception;

import java.io.Serial;

public class PermissionNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6226206922525682121L;

    private static final String DEFAULT_MESSAGE = """
            Permission not found!
            """;

    public PermissionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public PermissionNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
