package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.user.NewUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.UserResponse;
import com.tinytrades.tinytradesbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/")
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserResponseById(id);
    }

    @PostMapping("/users/")
    public UserResponse addUser(@RequestBody NewUserRequest newUserRequest) {
        return userService.addUser(newUserRequest);
    }


}
