package com.example.meatwaybackend.dto.user;

public record UserProfileResponse(
        String name,
        String surname,
        String city,
        String phone_number,
        String email
){
}