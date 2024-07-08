package com.security.rolepermissionexample.auth.service.impl;

import com.security.rolepermissionexample.auth.exception.UserAlreadyExistException;
import com.security.rolepermissionexample.auth.model.User;
import com.security.rolepermissionexample.auth.model.dto.request.RegisterRequest;
import com.security.rolepermissionexample.auth.model.entity.PermissionEntity;
import com.security.rolepermissionexample.auth.model.entity.RoleEntity;
import com.security.rolepermissionexample.auth.model.entity.UserEntity;
import com.security.rolepermissionexample.auth.model.mapper.RegisterRequestToUserEntityMapper;
import com.security.rolepermissionexample.auth.model.mapper.UserEntityToUserMapper;
import com.security.rolepermissionexample.auth.repository.PermissionRepository;
import com.security.rolepermissionexample.auth.repository.RoleRepository;
import com.security.rolepermissionexample.auth.repository.UserRepository;
import com.security.rolepermissionexample.base.AbstractBaseServiceTest;
import com.security.rolepermissionexample.builder.PermissionEntityBuilder;
import com.security.rolepermissionexample.builder.RoleEntityBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterServiceImplTest extends AbstractBaseServiceTest {

    @InjectMocks
    private RegisterServiceImpl registerService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PermissionRepository permissionRepository;

    private final RegisterRequestToUserEntityMapper registerRequestToUserEntityMapper = RegisterRequestToUserEntityMapper.initialize();

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();


    @Test
    void givenAdminRegisterRequest_whenRegisterAdmin_thenReturnAdmin() {

        // Given
        final RegisterRequest request = RegisterRequest.builder()
                .email("admincreate@example.com")
                .password("password123")
                .firstName("admin firstName")
                .lastName("admin lastName")
                .phoneNumber("123456789010")
                .role(List.of("ADMIN"))
                .permissions(List.of("admin:create"))
                .build();

        final String encodedPassword = "encodedPassword";

        final UserEntity userEntity = registerRequestToUserEntityMapper.mapForSaving(request);

        final User expected = userEntityToUserMapper.map(userEntity);

        // Mocking roleRepository
        RoleEntity adminRole = new RoleEntityBuilder()
                .withId(UUID.randomUUID().toString())
                .withName("ADMIN")
                .build();

        // Mocking permissionRepository
        PermissionEntity adminCreatePermission = new PermissionEntityBuilder()
                .withId(UUID.randomUUID().toString())
                .withName("admin:create")
                .build();
        adminCreatePermission.setId(UUID.randomUUID().toString());

        // When
        when(userRepository.existsUserEntityByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(adminRole));
        when(permissionRepository.findByName("admin:create")).thenReturn(Optional.of(adminCreatePermission));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Then
        User result = registerService.registerUser(request);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());

        // Verify
        verify(userRepository).save(any(UserEntity.class));

    }

    @Test
    void givenAdminRegisterRequest_whenEmailAlreadyExists_thenThrowAdminAlreadyExistException() {

        // Given
        final RegisterRequest request = RegisterRequest.builder()
                .email("admincreate@example.com")
                .password("password123")
                .firstName("admin firstName")
                .lastName("admin lastName")
                .phoneNumber("123456789010")
                .role(List.of("ADMIN"))
                .permissions(List.of("admin:create"))
                .build();

        // When
        when(userRepository.existsUserEntityByEmail(request.getEmail())).thenReturn(true);

        // Then
        assertThrows(UserAlreadyExistException.class, () -> registerService.registerUser(request));

        // Verify
        verify(userRepository, never()).save(any(UserEntity.class));

    }

}