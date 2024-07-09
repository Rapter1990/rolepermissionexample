package com.security.rolepermissionexample.product.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testAllArgsConstructor() {
        // Given
        String id = "test-id";
        String name = "test-name";
        BigDecimal amount = new BigDecimal("10.00");
        BigDecimal unitPrice = new BigDecimal("5.00");

        // When
        Product product = new Product(id, name, amount, unitPrice);

        // Then
        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(amount, product.getAmount());
        assertEquals(unitPrice, product.getUnitPrice());
    }

    @Test
    void testGettersAndSetters() {

        // Given
        Product product = new Product();

        // When
        product.setId("test-id");
        product.setName("test-name");
        product.setAmount(new BigDecimal("10.00"));
        product.setUnitPrice(new BigDecimal("5.00"));

        // Then
        assertEquals("test-id", product.getId());
        assertEquals("test-name", product.getName());
        assertEquals(new BigDecimal("10.00"), product.getAmount());
        assertEquals(new BigDecimal("5.00"), product.getUnitPrice());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        Product product1 = Product.builder()
                .id("test-id-1")
                .name("test-name-1")
                .amount(new BigDecimal("10.00"))
                .unitPrice(new BigDecimal("5.00"))
                .createdBy("test-user")
                .updatedBy("test-user")
                .build();

        Product product3 = Product.builder()
                .id("test-id-2")
                .name("test-name-2")
                .amount(new BigDecimal("20.00"))
                .unitPrice(new BigDecimal("10.00"))
                .createdBy("another-user")
                .updatedBy("another-user")
                .build();

        // Then
        assertNotEquals(product1, product3);
        assertNotEquals(product1.hashCode(), product3.hashCode());
    }

}