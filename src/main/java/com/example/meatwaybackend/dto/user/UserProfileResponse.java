package com.example.meatwaybackend.dto.user;

import com.example.meatwaybackend.dto.SaveRequestFileDto;

public record UserProfileResponse(
        Long id,
        String name,
        String surname,
        String city,
        String phone_number,
        String email,
        SaveRequestFileDto photo
) {
}