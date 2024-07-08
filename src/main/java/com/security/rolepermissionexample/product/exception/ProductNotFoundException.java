package com.security.rolepermissionexample.product.exception;

import java.io.Serial;

public class ProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5854010258697200749L;

    private static final String DEFAULT_MESSAGE = """
            Product not found!
            """;

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
