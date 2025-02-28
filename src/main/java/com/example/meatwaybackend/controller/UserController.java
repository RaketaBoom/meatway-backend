package com.example.meatwaybackend.controller;

import com.example.meatwaybackend.dto.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = UserController.USER_CONTROLLER, description = "API Профилей пользователей")
@RequestMapping(UserController.API_USER)
public class UserController {
    static final String USER_CONTROLLER = "user-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_USER = API_PREFIX + "/users";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить всех пользователей",
            tags = {USER_CONTROLLER}
    )
    public UserProfilesResponse findAllUsers(@RequestParam int page, @RequestParam int size) {
        //TODO business logic
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить пользователя по id",
            tags = {USER_CONTROLLER}
    )
    public UserProfileResponse findUserById(@PathVariable long id) {
        //TODO business logic
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать пользователя по id",
            tags = {USER_CONTROLLER}
    )
    public CreatedUserResponse createUser(@RequestBody UserCreateRequest userCreateRequest){
        //TODO business
        return null;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Внести изменения в профиль пользователя",
            tags = {USER_CONTROLLER}
    )
    public UserProfileResponse updateUser(@PathVariable long id, @RequestBody UserEditRequest updateRequest) {
        //TODO business
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Внести изменения в профиль пользователя",
            tags = {USER_CONTROLLER}
    )
    public void deleteUser(@PathVariable long id){
        //TODO business
    }
}
