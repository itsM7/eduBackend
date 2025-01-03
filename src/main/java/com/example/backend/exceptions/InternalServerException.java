package com.example.backend.exceptions;

import org.springframework.security.core.AuthenticationException;

//500
public class InternalServerException extends AuthenticationException{

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

}
