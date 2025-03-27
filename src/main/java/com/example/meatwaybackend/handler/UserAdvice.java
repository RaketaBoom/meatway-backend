package com.example.meatwaybackend.handler;

import com.example.meatwaybackend.dto.ErrorResponse;
import com.example.meatwaybackend.handler.exception.HttpCodeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAdvice {

    @ExceptionHandler(HttpCodeException.class)
    public ResponseEntity<ErrorResponse> handleHttpCodeException(HttpCodeException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getStatus().value(),
                        e.getMessage()
                ),
                e.getStatus()
        );
    }
}
