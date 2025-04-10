package com.example.meatwaybackend.handler.exception.auth;

import com.example.meatwaybackend.handler.exception.HttpCodeException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyInUseException extends HttpCodeException {
    private static final String MESSAGE_FORMAT = "Email %s already in use";
    public EmailAlreadyInUseException(String email) {
        super(HttpStatus.BAD_REQUEST, String.format(MESSAGE_FORMAT, email));
    }
}
