package com.example.meatwaybackend.handler.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "User with %s=%s not found";
    public UserNotFoundException(String field, String value) {
    }
}
