package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.user.UpdateUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.NewUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.UserResponse;
import com.tinytrades.tinytradesbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse findUserById(@PathVariable Long userId) {
        return userService.findUserResponseById(userId);
    }

    @PostMapping()
    public UserResponse addUser(@RequestBody NewUserRequest newUserRequest) {
        return userService.addUser(newUserRequest);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
        UserResponse userResponse = userService.updateUserById(userId, updateUserRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


}
