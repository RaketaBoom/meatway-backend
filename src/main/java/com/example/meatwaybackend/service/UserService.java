package com.example.meatwaybackend.service;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dto.user.CreatedUserResponse;
import com.example.meatwaybackend.dto.user.UserCreateRequest;
import com.example.meatwaybackend.dto.user.UserEditRequest;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import com.example.meatwaybackend.dto.user.UserProfilesResponse;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.UserMapper;
import com.example.meatwaybackend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserProfilesResponse findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository.findAll(pageable);

        return new UserProfilesResponse(
                userMapper.usersToUserProfilesResponse(usersPage.getContent()),
                usersPage.getSize()
        );
    }

    public UserProfileResponse findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));

        return userMapper.userToUserProfileResponse(user);
    }

    public CreatedUserResponse createUser(UserCreateRequest userCreateRequest) {
        //TODO обернуть в try catch вернуть saveUserException с 500 ошибкой
        User user = userRepository.save(userMapper.userCreateRequestToUser(userCreateRequest));

        return userMapper.userToCreatedUserResponse(user);
    }

    public UserProfileResponse patchUser(long id, UserEditRequest updateRequest) {
        User user = userMapper.userProfileResponseToUser(findById(id));
        userMapper.updateUserFromUserEditRequest(user, updateRequest);

        return userMapper.userToUserProfileResponse(userRepository.save(user));
    }

    public void removeUser(long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        userRepository.deleteById(id);
    }
}
