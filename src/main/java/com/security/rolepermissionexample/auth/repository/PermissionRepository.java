package com.security.rolepermissionexample.auth.repository;

import com.security.rolepermissionexample.auth.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface named {@link PermissionRepository} for managing {@link PermissionEntity} entities.
 */
public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {

    /**
     * Finds a permission entity by its name.
     *
     * @param name The name of the permission to find.
     * @return An {@link Optional} containing the found {@link PermissionEntity}, or empty if not found.
     */
    Optional<PermissionEntity> findByName(String name);
}
