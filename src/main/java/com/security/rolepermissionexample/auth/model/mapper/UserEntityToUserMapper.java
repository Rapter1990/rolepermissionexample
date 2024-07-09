package com.security.rolepermissionexample.auth.model.mapper;

import com.security.rolepermissionexample.auth.model.User;
import com.security.rolepermissionexample.auth.model.entity.UserEntity;
import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface namec {@link UserEntityToUserMapper} for mapping {@link UserEntity} to {@link User}.
 */
@Mapper
public interface UserEntityToUserMapper extends BaseMapper<UserEntity, User> {

    /**
     * Maps a {@link UserEntity} object to a {@link User} object.
     *
     * @param source The {@link UserEntity} object to map.
     * @return A {@link User} object mapped from the {@link UserEntity}.
     */
    @Override
    User map(UserEntity source);

    /**
     * Initializes and returns an instance of {@code UserEntityToUserMapper}.
     *
     * @return An initialized {@code UserEntityToUserMapper} instance.
     */
    static UserEntityToUserMapper initialize() {
        return Mappers.getMapper(UserEntityToUserMapper.class);
    }

}
