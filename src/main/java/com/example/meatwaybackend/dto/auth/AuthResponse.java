package com.example.meatwaybackend.dto.auth;

public record AuthResponse(
        String accessToken,
        String refreshToken
        ) {
}
