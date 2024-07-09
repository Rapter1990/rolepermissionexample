package com.security.rolepermissionexample.auth.model.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleEntityTest {

    @Test
    void testSuperBuilder() {

        // Given
        String id = "test-id";
        String name = "Admin";
        List<PermissionEntity> permissions = List.of(new PermissionEntity(), new PermissionEntity());

        // When
        RoleEntity roleEntity = RoleEntity.builder()
                .id(id)
                .name(name)
                .permissions(permissions)
                .build();

        // Then
        assertNotNull(roleEntity);
        assertEquals(id, roleEntity.getId());
        assertEquals(name, roleEntity.getName());
        assertEquals(permissions, roleEntity.getPermissions());

    }

    @Test
    void testAllArgsConstructor() {
        // Given
        String id = "test-id";
        String name = "Admin";
        List<PermissionEntity> permissions = List.of(new PermissionEntity(), new PermissionEntity());

        // When
        RoleEntity roleEntity = new RoleEntity(id, name, permissions);

        // Then
        assertNotNull(roleEntity);
        assertEquals(id, roleEntity.getId());
        assertEquals(name, roleEntity.getName());
        assertEquals(permissions, roleEntity.getPermissions());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        String id1 = "test-id-1";
        String id2 = "test-id-2";
        RoleEntity role1 = RoleEntity.builder().id(id1).build();
        RoleEntity role3 = RoleEntity.builder().id(id2).build();

        // Then
        assertNotEquals(role1, role3);
        assertNotEquals(role1.hashCode(), role3.hashCode());
    }

    @Test
    void testGettersAndSetters() {

        // Given
        String id = "test-id";
        String name = "Admin";
        List<PermissionEntity> permissions = List.of(new PermissionEntity(), new PermissionEntity());
        RoleEntity roleEntity = new RoleEntity();

        // When
        roleEntity.setId(id);
        roleEntity.setName(name);
        roleEntity.setPermissions(permissions);

        // Then
        assertEquals(id, roleEntity.getId());
        assertEquals(name, roleEntity.getName());
        assertEquals(permissions, roleEntity.getPermissions());

    }

}