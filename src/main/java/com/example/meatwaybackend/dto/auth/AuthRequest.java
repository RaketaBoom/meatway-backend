package com.example.meatwaybackend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(

        @Email
        @NotBlank
        String email,

        @Size(min = 3)
        @NotBlank
        String password
) {
}
