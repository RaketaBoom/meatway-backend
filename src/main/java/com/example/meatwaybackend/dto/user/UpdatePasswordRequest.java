package com.example.meatwaybackend.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest (
    @Size(min = 3)
    @NotBlank
    String password,

    @Size(min = 3)
    @NotBlank
    String confirmPassword
){}
