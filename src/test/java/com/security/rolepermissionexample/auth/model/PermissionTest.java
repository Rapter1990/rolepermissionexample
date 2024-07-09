package com.security.rolepermissionexample.auth.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermissionTest {


    @Test
    void testEqualsAndHashCode() {

        // Given
        Permission permission1 = Permission.builder().id("123").name("READ_PRIVILEGES").build();
        Permission permission3 = Permission.builder().id("456").name("WRITE_PRIVILEGES").build();

        // Then
        assertNotEquals(permission1, permission3);
        assertNotEquals(permission1.hashCode(), permission3.hashCode());

    }

}