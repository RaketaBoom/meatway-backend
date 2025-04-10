package com.example.meatwaybackend.handler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public abstract class HttpCodeException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}
