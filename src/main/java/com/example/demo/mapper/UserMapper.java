package com.example.demo.mapper;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AuthResponse toDto(UserEntity user) {
        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setUsername(user.getEmail());
        response.setRole(user.getRole());
        return response;
    }

    public UserEntity toEntity(AuthRequest dto) {
        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        return user;
    }
}