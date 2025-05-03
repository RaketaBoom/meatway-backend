package com.example.meatwaybackend.dto;

import java.time.LocalDateTime;

public record FileDto(
        String path,
        LocalDateTime createdAt
) {
}
