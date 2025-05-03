package com.example.meatwaybackend.dto.user;

import com.example.meatwaybackend.dto.SaveRequestFileDto;

public record UserEditRequest(
        String name,
        String surname,
        String city,
        String phoneNumber,
        SaveRequestFileDto photo
){
}