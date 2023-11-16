package com.tinytrades.tinytradesbackend.dto.user;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String username) {
}
