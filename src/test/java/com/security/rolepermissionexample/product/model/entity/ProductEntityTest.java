package com.security.rolepermissionexample.product.model.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {

    @Test
    void testAllArgsConstructor() {
        // Given
        String id = "test-id";
        String name = "test-name";
        BigDecimal amount = new BigDecimal("10.00");
        BigDecimal unitPrice = new BigDecimal("5.00");

        // When
        ProductEntity productEntity = new ProductEntity(id, name, amount, unitPrice);

        // Then
        assertNotNull(productEntity);
        assertEquals(id, productEntity.getId());
        assertEquals(name, productEntity.getName());
        assertEquals(amount, productEntity.getAmount());
        assertEquals(unitPrice, productEntity.getUnitPrice());
    }

    @Test
    void testEqualsAndHashCode() {

        // Given
        ProductEntity productEntity1 = ProductEntity.builder()
                .id("test-id-1")
                .name("test-name-1")
                .amount(new BigDecimal("10.00"))
                .unitPrice(new BigDecimal("5.00"))
                .build();

        ProductEntity productEntity3 = ProductEntity.builder()
                .id("test-id-2")
                .name("test-name-2")
                .amount(new BigDecimal("20.00"))
                .unitPrice(new BigDecimal("10.00"))
                .build();

        // Then
        assertNotEquals(productEntity1, productEntity3);
        assertNotEquals(productEntity1.hashCode(), productEntity3.hashCode());
    }

}