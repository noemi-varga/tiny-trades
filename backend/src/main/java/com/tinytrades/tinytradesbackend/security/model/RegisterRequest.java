package com.tinytrades.tinytradesbackend.security.model;

import lombok.Builder;

@Builder
public record RegisterRequest(String username, String firstName, String lastName, String email, String password) {
}
