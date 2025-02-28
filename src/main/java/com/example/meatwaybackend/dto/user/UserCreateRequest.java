package com.example.meatwaybackend.dto.user;

public record UserCreateRequest(
        String name,
        String surname,
        String email
){
}