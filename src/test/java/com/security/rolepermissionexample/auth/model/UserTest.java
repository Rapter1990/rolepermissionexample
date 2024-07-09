package com.security.rolepermissionexample.auth.model;

import com.security.rolepermissionexample.auth.model.enums.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testEqualsAndHashCode() {

        // Given
        Permission permission1 = Permission.builder().id("1").name("READ_PRIVILEGES").build();
        Permission permission2 = Permission.builder().id("2").name("WRITE_PRIVILEGES").build();
        Role role1 = Role.builder().id("role1").name("Admin").permissions(List.of(permission1)).build();
        Role role2 = Role.builder().id("role2").name("User").permissions(List.of(permission2)).build();

        User user1 = User.builder()
                .id("user123")
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .userStatus(UserStatus.ACTIVE)
                .roles(List.of(role1, role2))
                .permissions(List.of(permission1, permission2))
                .build();

        User user2 = User.builder()
                .id("user123")
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .userStatus(UserStatus.ACTIVE)
                .roles(List.of(role1, role2))
                .permissions(List.of(permission1, permission2))
                .build();

        User user3 = User.builder()
                .id("user456")
                .email("other@example.com")
                .firstName("Jane")
                .lastName("Doe")
                .phoneNumber("0987654321")
                .userStatus(UserStatus.PASSIVE)
                .roles(List.of(role1))
                .permissions(List.of(permission2))
                .build();

        // Then
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());

    }

}