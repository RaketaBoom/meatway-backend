package com.example.meatwaybackend.mapper;

import com.example.meatwaybackend.dto.FileDto;
import com.example.meatwaybackend.dto.SaveRequestFileDto;
import com.example.meatwaybackend.dto.register.UserCreateDTO;
import com.example.meatwaybackend.dto.register.UserDTO;
import com.example.meatwaybackend.dto.user.CreatedUserResponse;
import com.example.meatwaybackend.dto.user.UserCreateRequest;
import com.example.meatwaybackend.dto.user.UserEditRequest;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import com.example.meatwaybackend.model.File;
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

    File SaveRequestFileDtoToFile(SaveRequestFileDto saveRequestFileDto);
    SaveRequestFileDto FileToSaveRequestFileDto(File file);
    File FileDtoToFile(FileDto fileDto);
    FileDto FileDtoToFile(File file);

    User userProfileResponseToUser(UserProfileResponse userProfileResponse);

    User toUser(UserCreateDTO userCreateDTO);

    UserDTO toUserDto(User savedUser);
}
