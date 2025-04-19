package com.example.meatwaybackend.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Класс, представляющий собой DTO (Data Transfer Object) для создания нового пользователя.
 * Содержит информацию о пользователе.
 */
public record UserCreateDTO( //TODO Убрать confirmPassword

        @Email
        @NotBlank
        String email,

        @Size(min = 3)
        @NotBlank
        String password,

        @Size(min = 3)
        @NotBlank
        String confirmPassword
) {
}
