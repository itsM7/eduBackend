package com.example.backend.exceptions;

public class MessagingException extends RuntimeException {

    public MessagingException(String message) {
        super(message);
    }

    public MessagingException(String message, Throwable cause) {
        super(message, cause);
    }
}
