package com.example.meatwaybackend.controller;

import com.example.meatwaybackend.dto.register.UserDTO;
import com.example.meatwaybackend.dto.user.CreatedUserResponse;
import com.example.meatwaybackend.dto.user.UpdatePasswordRequest;
import com.example.meatwaybackend.dto.user.UserCreateRequest;
import com.example.meatwaybackend.dto.user.UserEditRequest;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import com.example.meatwaybackend.dto.user.UserProfilesResponse;
import com.example.meatwaybackend.service.UserService;
import com.example.meatwaybackend.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = UserController.USER_CONTROLLER, description = "API Профилей пользователей")
@RequestMapping(UserController.API_USER)
@Validated
@RequiredArgsConstructor
public class UserController {
    static final String USER_CONTROLLER = "user-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_USER = API_PREFIX + "/users";

    private final UserService userService;
    private final JWTUtils jwtUtils;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить всех пользователей",
            tags = {USER_CONTROLLER}
    )
    public UserProfilesResponse findAllUsers(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return userService.findAll(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить пользователя по id",
            tags = {USER_CONTROLLER}
    )
    public UserProfileResponse findUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать пользователя по id",
            tags = {USER_CONTROLLER}
    )
    public CreatedUserResponse createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Изменить пароль",
            tags = {USER_CONTROLLER}
    )
    public UserDTO updatePassword(@RequestBody @Valid UpdatePasswordRequest updateRequest, @AuthenticationPrincipal Jwt jwt) {
        return userService.updatePassword(jwtUtils.extractUsername(jwt.getTokenValue()), updateRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Внести изменения в профиль пользователя",
            tags = {USER_CONTROLLER}
    )
    public UserProfileResponse updateUser(@RequestBody @Valid UserEditRequest updateRequest, @AuthenticationPrincipal Jwt jwt) {
        return userService.patchUser(jwtUtils.extractUsername(jwt.getTokenValue()), updateRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить профиль пользователя",
            tags = {USER_CONTROLLER}
    )
    public void deleteUser(@AuthenticationPrincipal Jwt jwt) {
        userService.removeUser(jwtUtils.extractUsername(jwt.getTokenValue()));
    }
}
