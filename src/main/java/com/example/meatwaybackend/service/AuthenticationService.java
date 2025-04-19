package com.example.meatwaybackend.service;

import com.example.meatwaybackend.dto.auth.AuthRequest;
import com.example.meatwaybackend.dto.auth.AuthResponse;
import com.example.meatwaybackend.dto.auth.RefreshTokenRequest;
import com.example.meatwaybackend.utils.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest authRequest) {

        var authentication = new UsernamePasswordAuthenticationToken(
                authRequest.email(), authRequest.password());

        try {
            authenticationManager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Invalid email or password.", e);

        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Authentication error");
        }

        String jwtAccessToken = jwtUtils.generateAccessToken(authRequest.email());
        String jwtRefreshToken = jwtUtils.generateRefreshToken(authRequest.email());

        return new AuthResponse(jwtAccessToken, jwtRefreshToken);
    }

    public AuthResponse refreshToken(@Valid RefreshTokenRequest refreshTokenRequest) {
        String jwtAccessToken = jwtUtils.generateAccessToken(jwtUtils.extractUsername(refreshTokenRequest.token()));

        return new AuthResponse(jwtAccessToken, refreshTokenRequest.token());
    }
}