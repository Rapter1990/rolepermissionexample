package com.security.rolepermissionexample.auth.model.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InvalidTokenEntityTest {

    @Test
    void testSetters() {

        // Given
        InvalidTokenEntity invalidTokenEntity = new InvalidTokenEntity();
        String id = "test-id";
        String tokenId = "test-token-id";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "test-created-by";
        LocalDateTime updatedAt = LocalDateTime.now();
        String updatedBy = "test-updated-by";

        // When
        invalidTokenEntity.setId(id);
        invalidTokenEntity.setTokenId(tokenId);
        invalidTokenEntity.setCreatedAt(createdAt);
        invalidTokenEntity.setCreatedBy(createdBy);
        invalidTokenEntity.setUpdatedAt(updatedAt);
        invalidTokenEntity.setUpdatedBy(updatedBy);

        // Then
        assertEquals(id, invalidTokenEntity.getId());
        assertEquals(tokenId, invalidTokenEntity.getTokenId());
        assertEquals(createdAt, invalidTokenEntity.getCreatedAt());
        assertEquals(createdBy, invalidTokenEntity.getCreatedBy());
        assertEquals(updatedAt, invalidTokenEntity.getUpdatedAt());
        assertEquals(updatedBy, invalidTokenEntity.getUpdatedBy());

    }

    @Test
    void testAllArgsConstructor() {
        // Given
        String id = "test-id";
        String tokenId = "test-token-id";

        // When
        InvalidTokenEntity invalidTokenEntity = new InvalidTokenEntity(id,tokenId);

        // Then
        assertEquals(id, invalidTokenEntity.getId());
        assertEquals(tokenId, invalidTokenEntity.getTokenId());
    }

}