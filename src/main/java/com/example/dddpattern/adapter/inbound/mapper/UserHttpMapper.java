package com.example.dddpattern.adapter.inbound.mapper;

import com.example.dddpattern.adapter.inbound.model.request.UserRequest;
import com.example.dddpattern.adapter.inbound.model.response.UserResponse;

import com.example.dddpattern.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserHttpMapper {

    @Mapping(target = "name", source = "name")
    UserResponse from(User user);

    @Mapping(target = "name", source = "username")
    User from(UserRequest userRequest);
}
