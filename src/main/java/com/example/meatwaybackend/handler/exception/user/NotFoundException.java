package com.example.meatwaybackend.handler.exception.user;

import com.example.meatwaybackend.handler.exception.HttpCodeException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpCodeException {
    private static final String MESSAGE_TEMPLATE = "%s with id=%d not found";
    public NotFoundException(Class<?> aClass, long id) {
        super(HttpStatus.NOT_FOUND, MESSAGE_TEMPLATE.formatted(aClass.getCanonicalName(), id));
    }
}
