package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.example.demo.service.UserService;
import org.openapitools.api.AuthApi;
import org.openapitools.model.AuthResponse;
 import org.openapitools.model.AuthRequest;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final UserService userService;

    @Override
    public ResponseEntity<AuthResponse> authRegisterPost(AuthRequest authRequest) {
        AuthResponse response = userService.register(authRequest);
        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<AuthResponse> authLoginPost(AuthRequest authRequest) {
        return ResponseEntity.ok(userService.login(authRequest));
    }

}
