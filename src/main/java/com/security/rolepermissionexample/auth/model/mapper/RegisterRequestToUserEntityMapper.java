package com.security.rolepermissionexample.auth.model.mapper;

import com.security.rolepermissionexample.auth.model.dto.request.RegisterRequest;
import com.security.rolepermissionexample.auth.model.entity.UserEntity;
import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link RegisterRequestToUserEntityMapper} for mapping {@link RegisterRequest} to {@link UserEntity}.
 */
@Mapper
public interface RegisterRequestToUserEntityMapper extends BaseMapper<RegisterRequest, UserEntity> {

    /**
     * Maps a {@link RegisterRequest} instance to a {@link UserEntity} instance for saving purposes.
     *
     * @param registerRequest The {@link RegisterRequest} object to map.
     * @return A {@link UserEntity} object mapped from the {@link RegisterRequest}.
     */
    @Named("mapForSaving")
    default UserEntity mapForSaving(RegisterRequest registerRequest) {
        return UserEntity.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }

    /**
     * Initializes and returns an instance of {@code RegisterRequestToUserEntityMapper}.
     *
     * @return An initialized {@code RegisterRequestToUserEntityMapper} instance.
     */
    static RegisterRequestToUserEntityMapper initialize() {
        return Mappers.getMapper(RegisterRequestToUserEntityMapper.class);
    }

}
