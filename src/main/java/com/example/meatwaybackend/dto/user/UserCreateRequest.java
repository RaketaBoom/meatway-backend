package com.example.meatwaybackend.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String email,
        String city,
        String phoneNumber

) {
}