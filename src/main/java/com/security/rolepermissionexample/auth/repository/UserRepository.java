package com.security.rolepermissionexample.auth.repository;

import com.security.rolepermissionexample.auth.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface named {@link UserRepository} for managing {@link UserEntity} entities.
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

    /**
     * Checks if a user entity exists with the given email.
     *
     * @param email The email address to check.
     * @return {@code true} if a user entity exists with the email; otherwise {@code false}.
     */
    boolean existsUserEntityByEmail(final String email);

    /**
     * Finds a user entity by its email address.
     *
     * @param email The email address of the user to find.
     * @return An {@link Optional} containing the found {@link UserEntity}, or empty if not found.
     */
    Optional<UserEntity> findUserEntityByEmail(final String email);

}
