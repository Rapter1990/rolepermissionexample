package com.security.rolepermissionexample.auth.model.entity;

import com.security.rolepermissionexample.auth.model.enums.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void testAllArgsConstructor() {
        // Given
        String id = "test-id";
        String email = "test@example.com";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        UserStatus userStatus = UserStatus.ACTIVE;
        List<RoleEntity> roles = List.of(new RoleEntity(), new RoleEntity());

        // When
        UserEntity userEntity = new UserEntity(id, email, password, firstName, lastName, phoneNumber, userStatus, roles);

        // Then
        assertNotNull(userEntity);
        assertEquals(id, userEntity.getId());
        assertEquals(email, userEntity.getEmail());
        assertEquals(password, userEntity.getPassword());
        assertEquals(firstName, userEntity.getFirstName());
        assertEquals(lastName, userEntity.getLastName());
        assertEquals(phoneNumber, userEntity.getPhoneNumber());
        assertEquals(userStatus, userEntity.getUserStatus());
        assertEquals(roles, userEntity.getRoles());

    }

    @Test
    void testEqualsAndHashCode() {

        // Given
        String id1 = "test-id-1";
        String id2 = "test-id-2";
        UserEntity user1 = new UserEntity();
        user1.setId(id1);

        UserEntity user2 = new UserEntity();
        user2.setId(id1);

        UserEntity user3 = new UserEntity();
        user3.setId(id2);

        // Then
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

}