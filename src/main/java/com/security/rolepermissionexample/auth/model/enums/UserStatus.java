package com.security.rolepermissionexample.auth.model.enums;

/**
 * Enum representing the status of a user as {@link UserStatus}.
 * The status can be one of the following:
 * <ul>
 *   <li>{@link #ACTIVE} - The user is active and has full access.</li>
 *   <li>{@link #PASSIVE} - The user is passive and has limited or no access.</li>
 *   <li>{@link #SUSPENDED} - The user is suspended and temporarily has no access.</li>
 * </ul>
 */
public enum UserStatus {
    ACTIVE,
    PASSIVE,
    SUSPENDED
}
