package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.user.NewUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.UpdateUserRequest;
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

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public UserResponse findUserResponseById(Long id) {
        return UserMapper.mapToUserResponse(findUserById(id));
    }

    public UserResponse addUser(NewUserRequest newUserRequest) {
        User user = userRepository.save(UserMapper.mapToUser(newUserRequest));
        return UserMapper.mapToUserResponse(user);
    }

    public UserResponse updateUserById(Long id, UpdateUserRequest updateUserRequest) {
        User userToUpdate = findUserById(id);

        userToUpdate.setUsername(updateUserRequest.username());
        userToUpdate.setFirstName(updateUserRequest.firstName());
        userToUpdate.setLastName(updateUserRequest.lastName());
        userToUpdate.setEmail(updateUserRequest.email());
        userToUpdate.setPassword(updateUserRequest.password());
        userToUpdate.setLocation(updateUserRequest.location());
        userToUpdate.setPhoneNumber(userToUpdate.getPhoneNumber());

        User user = userRepository.save(userToUpdate);
        return UserMapper.mapToUserResponse(user);
    }

}
