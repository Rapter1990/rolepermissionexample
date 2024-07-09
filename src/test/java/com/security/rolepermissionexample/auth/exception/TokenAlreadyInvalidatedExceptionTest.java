package com.security.rolepermissionexample.auth.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenAlreadyInvalidatedExceptionTest {

    @Test
    void testDefaultConstructor() {
        TokenAlreadyInvalidatedException exception = new TokenAlreadyInvalidatedException();
        assertNotNull(exception);
        assertEquals("Token is already invalidated!\n", exception.getMessage());
    }

}