package com.example.login.controller;

import com.example.login.db.repository.UserRepository;
import com.example.login.db.repository.entity.UserEntity;
import com.example.login.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PasswordController {

    private final EmailUtil emailUtil;
    private final UserRepository userRepository;

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam String email) {

        // 2 option
        // Optional<UserEntity> user = userRepository.findByUsername(email);
        UserEntity user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body("Error: Email not found.");
        }

        // make link
        // or ver code (save to date base זמני)
        // or Token
        // or thread

        String resetLink = "https://eduportal.com/reset?token=example-token"; // Static or dynamically generated link
        String subject = "Reset Your Password";
        String body = "<p>Click the link below to reset your password:</p>"
                + "<a href='" + resetLink + "'>Reset Password</a>";

        // Send mail to specified user
        emailUtil.sendEmail(email, subject, body);

        return ResponseEntity.ok("Reset password email sent successfully to: " + email);
    }
}

