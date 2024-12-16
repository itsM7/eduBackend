package com.example.login.controller;

import com.example.login.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final EmailUtil emailUtil;

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email) {
        // link for password reset
        String resetLink = "https://eduportal.com/reset?token=example-token";

        String subject = "Reset Your Password";
        String body = "<p>Click the link below to reset your password:</p>"
                + "<a href='" + resetLink + "'>Reset Password</a>";

        emailUtil.sendEmail(email, subject, body);

        return ResponseEntity.ok("Reset password email sent successfully to: " + email);
    }
}

