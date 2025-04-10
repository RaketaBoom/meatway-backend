package com.example.meatwaybackend.handler.exception.auth;

import com.example.meatwaybackend.handler.exception.HttpCodeException;
import org.springframework.http.HttpStatus;

public class NonValidPasswordException extends HttpCodeException {
    private final static String MESSAGE = "Non valid password";

    public NonValidPasswordException(){
        super(HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
