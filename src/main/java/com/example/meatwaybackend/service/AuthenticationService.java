package com.example.meatwaybackend.service;

import com.example.meatwaybackend.dto.auth.AuthRequest;
import com.example.meatwaybackend.dto.auth.AuthResponse;
import com.example.meatwaybackend.utils.JWTUtils;
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

        String jwtToken = jwtUtils.generateToken(authRequest.email());

        return new AuthResponse(jwtToken);
    }
}