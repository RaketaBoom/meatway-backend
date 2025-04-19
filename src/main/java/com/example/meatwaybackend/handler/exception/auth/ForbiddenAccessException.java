package com.example.meatwaybackend.handler.exception.auth;

import com.example.meatwaybackend.handler.exception.HttpCodeException;
import org.springframework.http.HttpStatus;

public class ForbiddenAccessException extends HttpCodeException {
    private static final String MESSAGE_FORMAT = "Отказано в доступе пользователю %s";
    public ForbiddenAccessException(String email) {
        super(HttpStatus.FORBIDDEN, MESSAGE_FORMAT.formatted(email));
    }
}
