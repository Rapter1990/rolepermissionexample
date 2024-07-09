package com.security.rolepermissionexample.auth.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        RoleNotFoundException exception = new RoleNotFoundException();
        assertNotNull(exception);
        assertEquals("Role not found!\n", exception.getMessage());
    }

}