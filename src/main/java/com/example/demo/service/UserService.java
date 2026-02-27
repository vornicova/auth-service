package com.example.demo.service;

import org.openapitools.model.AuthRequest;
import org.openapitools.model.AuthResponse;

public interface UserService {
    AuthResponse register(AuthRequest request);
    AuthResponse getByUsername(String username);
    AuthResponse login(AuthRequest request);
}