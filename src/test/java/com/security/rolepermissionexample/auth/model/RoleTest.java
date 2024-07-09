package com.security.rolepermissionexample.auth.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testEqualsAndHashCode() {

        // Given
        Permission permission1 = Permission.builder().id("1").name("READ_PRIVILEGES").build();
        Permission permission2 = Permission.builder().id("2").name("WRITE_PRIVILEGES").build();

        Role role1 = Role.builder()
                .id("123")
                .name("Admin")
                .permissions(List.of(permission1, permission2))
                .build();

        Role role2 = Role.builder()
                .id("123")
                .name("Admin")
                .permissions(List.of(permission1, permission2))
                .build();

        Role role3 = Role.builder()
                .id("456")
                .name("User")
                .permissions(List.of(permission1))
                .build();

        // Then
        assertNotEquals(role1, role3);
        assertNotEquals(role1.hashCode(), role3.hashCode());

    }

}