package com.example.meatwaybackend.controller;

import com.example.meatwaybackend.dto.auth.AuthRequest;
import com.example.meatwaybackend.dto.auth.AuthResponse;
import com.example.meatwaybackend.dto.auth.RefreshTokenRequest;
import com.example.meatwaybackend.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/api/v1/auth/login")
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest authRequest) {

        return authenticationService.authenticate(authRequest);
    }

    @PostMapping("/api/v1/auth/refresh")
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

        return authenticationService.refreshToken(refreshTokenRequest);
    }
}
