package com.example.meatwaybackend.handler.exception.auth;

import com.example.meatwaybackend.handler.exception.HttpCodeException;
import org.springframework.http.HttpStatus;

public class PasswordsNotMatchException extends HttpCodeException {
    private static final String MESSAGE = "Passwords do not match";

    public PasswordsNotMatchException() {
        super(HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
