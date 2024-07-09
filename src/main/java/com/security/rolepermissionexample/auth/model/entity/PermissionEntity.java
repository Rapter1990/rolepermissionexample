package com.security.rolepermissionexample.auth.model.entity;

import com.security.rolepermissionexample.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an entity as {@link PermissionEntity} for permissions.
 */
@Entity
@Getter
@Setter
@Table(name = "PERMISSIONS")
public class PermissionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

}
