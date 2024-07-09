package com.security.rolepermissionexample.auth.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTypeTest {

    @Test
    void testTokenTypeValue() {
        // Given
        TokenType tokenType = TokenType.BEARER;

        // When
        String value = tokenType.getValue();

        // Then
        assertEquals("Bearer", value);
    }

    @Test
    void testTokenTypeEnum() {
        // Given
        TokenType tokenType = TokenType.BEARER;

        // When
        TokenType[] tokenTypes = TokenType.values();

        // Then
        assertTrue(tokenTypes.length == 1);
        assertEquals(TokenType.BEARER, tokenTypes[0]);
    }

    @Test
    void testValueOfTokenType() {
        // Given
        String tokenTypeName = "BEARER";

        // When
        TokenType tokenType = TokenType.valueOf(tokenTypeName);

        // Then
        assertEquals(TokenType.BEARER, tokenType);
        assertEquals("Bearer", tokenType.getValue());
    }

}