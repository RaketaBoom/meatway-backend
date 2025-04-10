package com.example.meatwaybackend.service;

import com.example.meatwaybackend.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public String hashingPassword(String password) {
        PasswordUtils.validatePassword(password);

        return passwordEncoder.encode(password);
    }
}
