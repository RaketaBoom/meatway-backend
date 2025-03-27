package com.example.meatwaybackend.handler.exception.user;

import com.example.meatwaybackend.handler.exception.HttpCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HttpCodeException {
    private static final String MESSAGE_TEMPLATE = "User with id=%d not found";
    public UserNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, MESSAGE_TEMPLATE.formatted(id));
    }
}
