package com.security.rolepermissionexample.product.model.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductResponseTest {

    @Test
    void testNoArgsConstructor() {
        // When
        ProductResponse productResponse = new ProductResponse();

        // Then
        assertNotNull(productResponse);
        assertNull(productResponse.getId());
        assertNull(productResponse.getName());
        assertNull(productResponse.getAmount());
        assertNull(productResponse.getUnitPrice());
    }

    @Test
    void testSetters() {

        // Given
        String id = "test-id";
        String name = "test-name";
        BigDecimal amount = new BigDecimal("10.00");
        BigDecimal unitPrice = new BigDecimal("5.00");

        // When
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(id);
        productResponse.setName(name);
        productResponse.setAmount(amount);
        productResponse.setUnitPrice(unitPrice);

        // Then
        assertEquals(id, productResponse.getId());
        assertEquals(name, productResponse.getName());
        assertEquals(amount, productResponse.getAmount());
        assertEquals(unitPrice, productResponse.getUnitPrice());

    }

}