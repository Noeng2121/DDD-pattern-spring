package com.example.dddpattern.infrastructure.database.mapper;

import com.example.dddpattern.domain.entity.User;
import com.example.dddpattern.infrastructure.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface JpaUserMapper {
    @Mapping(target = "name", source = "username")
    User from(UserEntity user);

    @Mapping(target = "username", source = "name")
    UserEntity from(User user);
}
