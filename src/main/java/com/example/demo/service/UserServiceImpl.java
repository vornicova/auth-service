package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.kafka.event.UserCreatedEvent;
import com.example.demo.kafka.producer.UserKafkaProducer;
import com.example.demo.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserKafkaProducer producer;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserKafkaProducer producer, UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.producer = producer;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(AuthRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        UserEntity user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        user = userRepository.save(user);

        producer.sendUserCreated(
                UserCreatedEvent.builder()
                        .authUserId(user.getId())
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .role(user.getRole())
                        .phone(user.getPhone())
                        .build()
        );

        String token = jwtUtil.generateToken(user.getEmail());

        AuthResponse dto = userMapper.toDto(user);
        dto.setToken(token);

        return dto;
    }

    @Override
    public AuthResponse getByUsername(String username) {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toDto(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        AuthResponse dto = userMapper.toDto(user);
        dto.setToken(token);

        return dto;
    }

}