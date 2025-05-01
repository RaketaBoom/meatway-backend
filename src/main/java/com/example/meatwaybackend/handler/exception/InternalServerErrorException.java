package com.example.meatwaybackend.handler.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpCodeException {
    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
