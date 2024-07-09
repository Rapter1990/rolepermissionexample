package com.security.rolepermissionexample.auth.model;

import com.security.rolepermissionexample.common.model.BaseDomainModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a permission domain object as {@link Permission} in the application.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseDomainModel {
    private String id;
    private String name;
}
