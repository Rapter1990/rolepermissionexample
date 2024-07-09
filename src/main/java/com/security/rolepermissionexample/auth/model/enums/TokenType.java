package com.security.rolepermissionexample.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing the type of token used in the authentication system as {@link TokenType}.
 * Each enum constant holds a string value corresponding to the token type.
 */
@Getter
@RequiredArgsConstructor
public enum TokenType {

    BEARER("Bearer");

    private final String value;

}
