package com.example.login.service.impl;

import com.example.login.db.repository.UserRepository;
import com.example.login.db.repository.entity.UserEntity;
import com.example.login.dto.request.LoginRequest;
import com.example.login.dto.request.RegisterRequest;
import com.example.login.exceptions.BadRequestException;
import com.example.login.security.JwtUtil;
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
        System.out.println("Searching for user: " + loginRequest.getUsername() + " or " + loginRequest.getEmail());

        UserEntity user = null;

        // البحث عن المستخدم بواسطة username أو email
        if (loginRequest.getUsername() != null) {
            user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new BadRequestException("User not found"));
        } else if (loginRequest.getEmail() != null) {
            user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new BadRequestException("User not found"));
        } else {
            throw new BadRequestException("Username or email must be provided");
        }

        System.out.println("Checking password...");
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            System.out.println("Incorrect password for user: " + user.getUsername());
            throw new BadRequestException("Invalid password");
        }

        System.out.println("Password matched successfully!");
        return jwtUtil.generateToken(user.getUsername());
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