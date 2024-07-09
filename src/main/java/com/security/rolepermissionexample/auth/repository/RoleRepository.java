package com.security.rolepermissionexample.auth.repository;

import com.security.rolepermissionexample.auth.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface named {@link RoleRepository} for managing {@link RoleEntity} entities.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    /**
     * Finds a role entity by its name.
     *
     * @param name The name of the role to find.
     * @return An {@link Optional} containing the found {@link RoleEntity}, or empty if not found.
     */
    Optional<RoleEntity> findByName(String name);
}
