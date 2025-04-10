package com.example.meatwaybackend.service;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dto.register.UserCreateDTO;
import com.example.meatwaybackend.dto.register.UserDTO;
import com.example.meatwaybackend.handler.exception.auth.EmailAlreadyInUseException;
import com.example.meatwaybackend.mapper.UserMapper;
import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordService passwordService;

    public UserDTO create(UserCreateDTO userCreateDTO) {

        checkEmailUnique(userCreateDTO.email());

        PasswordUtils.validatePasswordMatch(userCreateDTO.password(), userCreateDTO.confirmPassword());

        User user = userMapper.toUser(userCreateDTO);

        String hashedPassword = passwordService.hashingPassword(userCreateDTO.password());
        user.setPasswordDigest(hashedPassword);

        User savedUser = userRepository.save(user);

        log.debug("User with email '{}' successfully created", user.getEmail());

        return userMapper.toUserDto(savedUser);
    }

    private void checkEmailUnique(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyInUseException(email);
        }
    }
}