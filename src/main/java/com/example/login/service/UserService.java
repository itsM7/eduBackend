package com.example.login.service;

import com.example.login.dto.request.LoginRequest;
import com.example.login.dto.request.RegisterRequest;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String authenticateUser(@NonNull LoginRequest loginRequest);
    void registerUser(@NonNull RegisterRequest registerRequest);
    String generateToken(String username);
}