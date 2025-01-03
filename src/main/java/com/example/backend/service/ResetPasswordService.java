package com.example.backend.service;

public interface ResetPasswordService {

    /**
     * Sends a reset code to the specified email.
     *
     * @param email The email to which the reset code will be sent.
     * @return A message indicating that the code was sent.
     */
    String sendResetCode(String email);

    /**
     * Resets the user's password.
     *
     * @param email The email associated with the reset token.
     * @param token The reset token provided by the user.
     * @param newPassword The new password to set for the user.
     * @return A message indicating success or failure.
     */
    String resetPassword(String email, String token, String newPassword);
}
