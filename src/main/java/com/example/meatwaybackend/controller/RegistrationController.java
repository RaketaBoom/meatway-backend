package com.example.meatwaybackend.controller;

import com.example.meatwaybackend.dto.register.UserCreateDTO;
import com.example.meatwaybackend.dto.register.UserDTO;
import com.example.meatwaybackend.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class  RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/api/v1/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserCreateDTO userCreateDTO) {

        return registrationService.create(userCreateDTO);

    }
}
