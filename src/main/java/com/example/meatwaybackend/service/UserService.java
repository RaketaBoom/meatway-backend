package com.example.meatwaybackend.service;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dto.user.CreatedUserResponse;
import com.example.meatwaybackend.dto.user.UserCreateRequest;
import com.example.meatwaybackend.dto.user.UserEditRequest;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import com.example.meatwaybackend.dto.user.UserProfilesResponse;
import com.example.meatwaybackend.handler.exception.UserNotFoundException;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.UserMapper;
import com.example.meatwaybackend.model.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsManager {
    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_SIZE = 10;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserProfilesResponse findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(DEFAULT_PAGE),
                Optional.ofNullable(size).orElse(DEFAULT_SIZE)
        );
        Page<User> usersPage = userRepository.findAll(pageable);

        return new UserProfilesResponse(
                userMapper.usersToUserProfilesResponse(usersPage.getContent()),
                usersPage.getTotalElements()
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

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }

    @Override
    public void createUser(UserDetails userData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public void updateUser(UserDetails user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    @Override
    public boolean userExists(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userExists'");
    }
}
