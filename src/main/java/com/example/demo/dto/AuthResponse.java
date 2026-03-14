package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private Long id;
    private String username;
    private String role;
    private String token;

}