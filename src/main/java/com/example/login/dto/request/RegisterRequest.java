package com.example.login.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegisterRequest {
    String username;
    String email;
    String password;
}
