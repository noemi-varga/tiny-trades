package com.tinytrades.tinytradesbackend.mapper;

import com.tinytrades.tinytradesbackend.dto.user.NewUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.UserResponse;
import com.tinytrades.tinytradesbackend.model.user.User;

public class UserMapper {

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public static User mapToUser(NewUserRequest newUserRequest) {
        return User
                .builder()
                .username(newUserRequest.username())
                .firstName(newUserRequest.firstName())
                .lastName(newUserRequest.lastName())
                .email(newUserRequest.email())
                .password(newUserRequest.password())
                .phoneNumber(newUserRequest.phoneNumber())
                .location(newUserRequest.location())
                .build();
    }
}
