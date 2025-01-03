package com.example.backend.service.impl;

import com.example.backend.repository.UserRepository;
import com.example.backend.entity.ResetPasswordEntity;
import com.example.backend.repository.ResetPasswordRepository;
import com.example.backend.util.EmailUtil;
import com.example.backend.service.ResetPasswordService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordServiceImpl.class);

    @Autowired
    private ResetPasswordRepository resetPasswordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String sendResetCode(String email) {
        if (!userRepository.existsByEmail(email)) {
            logger.error("Email not found in the system: {}", email);
            throw new IllegalArgumentException("Email not found in the system.");
        }

        // Delete existing reset tokens for the email
        resetPasswordRepository.deleteAllByEmail(email);

        // Generate a random token
        String token = UUID.randomUUID().toString();

        ResetPasswordEntity resetEntity = new ResetPasswordEntity();
        resetEntity.setEmail(email);
        resetEntity.setToken(token);
        resetEntity.setExpiryDate(LocalDateTime.now().plusMinutes(10));

        // Save in database
        resetPasswordRepository.save(resetEntity);

        emailUtil.sendEmail(
                email,
                "Reset Password Code",
                String.format(
                        "<p>Your reset code is: <strong>%s</strong></p>" +
                                "<p>This code will expire in 10 minutes.</p>",
                        token
                )
        );
        logger.info("Reset code sent to email: {}", email);
        return "Reset code sent to your email.";
    }

    @Override
    public String resetPassword(String email, String token, String newPassword) {
        // Find the reset token
        Optional<ResetPasswordEntity> resetEntity = resetPasswordRepository.findByToken(token);

        if (resetEntity.isEmpty()) {
            logger.error("Reset token not found for token: {}", token);
            throw new IllegalArgumentException("Reset token not found in the system. Please request a new reset code.");
        }

        if (resetEntity.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            logger.error("Reset token expired for token: {}", token);
            throw new IllegalArgumentException("Reset token expired. Please request a new one.");
        }

        if (!resetEntity.get().getEmail().equals(email)) {
            logger.error("Email mismatch: provided {}, expected {}", email, resetEntity.get().getEmail());
            throw new IllegalArgumentException("Email does not match the reset token.");
        }

        // Hash the new password
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Update the user's password
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setPassword(encodedPassword);
            userRepository.save(user);
        });

        // Delete the used reset token
        resetPasswordRepository.delete(resetEntity.get());

        logger.info("Password reset successfully for email: {}", email);

        return "Password reset successfully.";
    }
}