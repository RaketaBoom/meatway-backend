package com.example.meatwaybackend.mapper;

import com.example.meatwaybackend.dto.user.CreatedUserResponse;
import com.example.meatwaybackend.dto.user.UserCreateRequest;
import com.example.meatwaybackend.dto.user.UserEditRequest;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import com.example.meatwaybackend.model.User;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserProfileResponse userToUserProfileResponse(User user);

    List<UserProfileResponse> usersToUserProfilesResponse(List<User> users);

    User userCreateRequestToUser(UserCreateRequest userCreateRequest);

    CreatedUserResponse userToCreatedUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserEditRequest(@MappingTarget User user, UserEditRequest userEditRequest);

    User userProfileResponseToUser(UserProfileResponse userProfileResponse);
}
