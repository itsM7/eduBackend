package com.example.backend.service.impl;

import com.example.backend.repository.UserRepository;
import com.example.backend.entity.UserEntity;
import com.example.backend.dto.request.LoginRequest;
import com.example.backend.dto.request.RegisterRequest;
import com.example.backend.exceptions.BadRequestException;
import com.example.backend.security.JwtUtil;
import com.example.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register user
    @Override
    public void registerUser(@NonNull RegisterRequest registerRequest) {
        //check null
        if (registerRequest.getUsername() == null || registerRequest.getEmail() == null || registerRequest.getPassword() == null) {
            throw new BadRequestException("Username, email, and password are required.");
        }
        // check if already exists
        if (userRepository.findByEmailOrUsername(registerRequest.getEmail(), registerRequest.getUsername()).isPresent()) {
            throw new BadRequestException("User already exists");
        }

        // hash
        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());


        // or use allargsCons
        UserEntity user = new UserEntity();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
        System.out.println("User registered successfully with username: " + registerRequest.getUsername());
    }

    // Authenticate user
    @Override
    public String authenticateUser(@NonNull LoginRequest loginRequest) {

        UserEntity user;
        if (loginRequest.getUsername() != null) {
            user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new BadRequestException("User not found"));
        } else if (loginRequest.getEmail() != null) {
            user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new BadRequestException("User not found"));
        } else {
            throw new BadRequestException("Username or email must be provided");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }
        return jwtUtil.generateToken(user.getUsername());
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));

        // hash the new passwordd
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        // save the new password
        userRepository.save(user);
        System.out.println("Password updated successfully for user: " + email);
    }

//    @Override
//    public void authenticateUser(@NonNull LoginRequest loginRequest){
//        userRepository.findByEmail(loginRequest.getUsernameOrEmail())
//               .ifPresentOrElse(
//                user -> validatePassword(loginRequest),
//                       () ->  new BadRequestException("User not found"));
//    }
//    @Override
//    public String generateToken(@NonNull String username) {
//        return "";
//    }
//    private void validatePassword(@NonNull LoginRequest loginRequest) {
//        String password = loginRequest.getPassword();
//        System.out.println("Validating password for: " + loginRequest.getEmail());
}