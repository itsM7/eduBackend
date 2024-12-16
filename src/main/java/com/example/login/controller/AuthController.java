package com.example.login.controller;

import com.example.login.dto.request.LoginRequest;
import com.example.login.dto.request.RegisterRequest;
import com.example.login.service.UserService;
import com.example.login.security.JwtUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(@NonNull UserService userService,@NonNull JwtUtil jwtUtil ) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String username = userService.authenticateUser(request);
        String tokenResponse = userService.generateToken(username);
        return ResponseEntity.ok(tokenResponse);
    }
}