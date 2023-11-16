package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.user.NewUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.UserResponse;
import com.tinytrades.tinytradesbackend.mapper.UserMapper;
import com.tinytrades.tinytradesbackend.model.User;
import com.tinytrades.tinytradesbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserResponse).toList();
    }

    public UserResponse findUserResponseById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        return UserMapper.mapToUserResponse(user);
    }

    public UserResponse addUser(NewUserRequest newUserRequest) {
        User user = userRepository.save(UserMapper.mapToUser(newUserRequest));
        return UserMapper.mapToUserResponse(user);
    }
}
