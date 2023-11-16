package com.tinytrades.tinytradesbackend.dto.user;

public record UpdateUserRequest(String username, String firstName, String lastName, String email, String password, String location, String phoneNumber) {
}
