package com.example.backend.service;

import com.example.backend.dto.request.LoginRequest;
import com.example.backend.dto.request.RegisterRequest;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String authenticateUser(@NonNull LoginRequest loginRequest);
    void registerUser(@NonNull RegisterRequest registerRequest);
    void updatePassword(String email, String newPassword);
}