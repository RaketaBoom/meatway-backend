package com.example.meatwaybackend.dto.user;

public record UserEditRequest(
        String name,
        String surname,
        String city,
        String phone_number
){
}