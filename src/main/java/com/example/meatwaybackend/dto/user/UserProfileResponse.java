package com.example.meatwaybackend.dto.user;

public record UserProfileResponse(
        Long id,
        String name,
        String surname,
        String city,
        String phone_number,
        String email
){
}