package com.security.rolepermissionexample.common.model.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    @Test
    void testGettersAndSetters() {

        BaseEntity entity = new BaseEntity();

        // Test setters
        entity.setCreatedBy("TestUser");
        entity.setUpdatedBy("AnotherUser");

        // Test getters
        assertEquals("TestUser", entity.getCreatedBy());
        assertEquals("AnotherUser", entity.getUpdatedBy());

    }

    @Test
    void testSuperBuilder() {

        BaseEntity entity = BaseEntity.builder()
                .createdAt(LocalDateTime.now())
                .createdBy("TestUser")
                .updatedAt(LocalDateTime.now())
                .updatedBy("AnotherUser")
                .build();

        assertNotNull(entity);
        assertEquals("TestUser", entity.getCreatedBy());
        assertEquals("AnotherUser", entity.getUpdatedBy());

    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime createdAt = LocalDateTime.of(2023, 1, 1, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 1, 2, 12, 0);
        BaseEntity entity = new BaseEntity(createdAt, "TestUser", updatedAt, "AnotherUser");

        assertNotNull(entity);
        assertEquals("TestUser", entity.getCreatedBy());
        assertEquals("AnotherUser", entity.getUpdatedBy());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(updatedAt, entity.getUpdatedAt());
    }

}