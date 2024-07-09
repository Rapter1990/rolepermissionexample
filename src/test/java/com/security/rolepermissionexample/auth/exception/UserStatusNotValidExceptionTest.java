package com.security.rolepermissionexample.auth.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStatusNotValidExceptionTest {

    @Test
    void testDefaultConstructor() {
        UserStatusNotValidException exception = new UserStatusNotValidException();
        assertNotNull(exception);
        assertEquals("User status is not valid!\n", exception.getMessage());
    }

}