package com.security.rolepermissionexample.builder;

import com.security.rolepermissionexample.auth.model.entity.PermissionEntity;
import com.security.rolepermissionexample.auth.model.entity.RoleEntity;
import com.security.rolepermissionexample.auth.model.entity.UserEntity;
import com.security.rolepermissionexample.auth.model.enums.UserStatus;
import org.hibernate.usertype.UserType;

import java.util.List;
import java.util.UUID;

public class UserEntityBuilder extends BaseBuilder<UserEntity> {

    public UserEntityBuilder() {
        super(UserEntity.class);
    }

    public UserEntityBuilder withValidUserFields() {
        PermissionEntity userGetPermission = new PermissionEntityBuilder()
                .withValidFields()
                .withName("user:get")
                .build();

        RoleEntity userRole = new RoleEntityBuilder()
                .withValidFields()
                .withName("User")
                .withPermissions(List.of(userGetPermission))
                .build();

        return this
                .withId(UUID.randomUUID().toString())
                .withEmail("userexample@example.com")
                .withPassword("userpassword")
                .withFirstName("User")
                .withLastName("Userlastname")
                .withPhoneNumber("123456789011")
                .withUserStatus(UserStatus.ACTIVE)
                .withRoles(List.of(userRole));
    }

    public UserEntityBuilder withValidAdminFields() {
        PermissionEntity createPermission = new PermissionEntityBuilder()
                .withValidFields()
                .withName("admin:create")
                .build();

        PermissionEntity getPermission = new PermissionEntityBuilder()
                .withValidFields()
                .withName("admin:get")
                .build();

        PermissionEntity updatePermission = new PermissionEntityBuilder()
                .withValidFields()
                .withName("admin:update")
                .build();

        PermissionEntity deletePermission = new PermissionEntityBuilder()
                .withValidFields()
                .withName("admin:delete")
                .build();

        RoleEntity adminRole = new RoleEntityBuilder()
                .withValidFields()
                .withName("Admin")
                .withPermissions(List.of(createPermission, getPermission, updatePermission, deletePermission))
                .build();

        return this
                .withId(UUID.randomUUID().toString())
                .withEmail("adminexample@example.com")
                .withPassword("adminpassword")
                .withFirstName("Admin")
                .withLastName("Adminlastname")
                .withPhoneNumber("1234567890112")
                .withUserStatus(UserStatus.ACTIVE)
                .withRoles(List.of(adminRole));
    }


    public UserEntityBuilder withId(String id) {
        data.setId(id);
        return this;
    }

    public UserEntityBuilder withEmail(String email) {
        data.setEmail(email);
        return this;
    }

    public UserEntityBuilder withPassword(String password) {
        data.setPassword(password);
        return this;
    }

    public UserEntityBuilder withFirstName(String firstName) {
        data.setFirstName(firstName);
        return this;
    }

    public UserEntityBuilder withLastName(String lastName) {
        data.setLastName(lastName);
        return this;
    }

    public UserEntityBuilder withPhoneNumber(String phoneNumber) {
        data.setPhoneNumber(phoneNumber);
        return this;
    }

    public UserEntityBuilder withUserStatus(UserStatus userStatus) {
        data.setUserStatus(userStatus);
        return this;
    }

    public UserEntityBuilder withRoles(List<RoleEntity> roles) {
        data.setRoles(roles);
        return this;
    }

}
