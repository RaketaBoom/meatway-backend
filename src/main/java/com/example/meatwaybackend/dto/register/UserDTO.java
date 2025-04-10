package com.example.meatwaybackend.dto.register;

import java.time.LocalDate;

/**
 * Класс, представляющий собой DTO (Data Transfer Object) для представления существующего пользователя.
 * Содержит идентификатор и информацию о пользователе.
 */
public record UserDTO(

        long id,
        String email,
        LocalDate createdAt
) {
}
