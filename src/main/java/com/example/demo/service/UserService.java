package com.example.demo.service;


import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface UserService {
    AuthResponse register(AuthRequest request);
    AuthResponse getByUsername(String username);
    AuthResponse login(AuthRequest request);
}