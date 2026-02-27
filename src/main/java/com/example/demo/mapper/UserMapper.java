package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.openapitools.model.AuthRequest;
import org.openapitools.model.AuthResponse;
import com.example.demo.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AuthResponse toDto(UserEntity user);

    UserEntity toEntity(AuthRequest dto);
}