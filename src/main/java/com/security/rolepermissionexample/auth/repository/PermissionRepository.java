package com.security.rolepermissionexample.auth.repository;

import com.security.rolepermissionexample.auth.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {

    Optional<PermissionEntity> findByName(String name);
}
