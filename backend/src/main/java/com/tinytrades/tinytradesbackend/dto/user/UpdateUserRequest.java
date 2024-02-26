package com.tinytrades.tinytradesbackend.dto.user;

public record UpdateUserRequest(String firstName, String lastName, String password, String location, String phoneNumber) {
}
