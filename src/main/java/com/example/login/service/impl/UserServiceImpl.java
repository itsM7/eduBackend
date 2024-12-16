package com.example.login.service.impl;

import com.example.login.db.repository.UserRepository;
import com.example.login.db.repository.entity.UserEntity;
import com.example.login.dto.request.LoginRequest;
import com.example.login.dto.request.RegisterRequest;
import com.example.login.exceptions.BadRequestException;
import com.example.login.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Register user
    @Override
    public void registerUser(@NonNull RegisterRequest registerRequest) {
        if (userRepository.findByEmailOrUsername(registerRequest.getEmail(), registerRequest.getUsername()).isPresent()) {
            throw new BadRequestException("User already exists");
        }

        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());

        UserEntity user = new UserEntity();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
    }

    // Authenticate user
    @Override
    public String authenticateUser(@NonNull LoginRequest loginRequest) {
        String usernameOrEmail = loginRequest.getUsernameOrEmail();
        UserEntity user = userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new BadRequestException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }
        return usernameOrEmail;
    }

    // Generate token
    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, "secret_key")
                .compact();
    }

//    @Override
//    public void authenticateUser(@NonNull LoginRequest loginRequest){
//        userRepository.findByEmailOrUsername(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail())
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