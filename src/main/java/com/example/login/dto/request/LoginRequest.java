package com.example.login.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Value
public class LoginRequest {
    String usernameOrEmail;
    String password;
}
