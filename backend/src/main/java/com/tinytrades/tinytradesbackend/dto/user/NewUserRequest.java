package com.tinytrades.tinytradesbackend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserRequest(
        @NotBlank(message = "Username must not be blank") String username,
        @NotBlank(message = "First name must not be blank") String firstName,
        @NotBlank(message = "Last name must not be blank") String lastName,
        @Email(message = "Email must be valid") String email,
        @Size(min = 8, message = "Password must be at least 8 characters long") String password,
        String location,
        String phoneNumber) {
}
