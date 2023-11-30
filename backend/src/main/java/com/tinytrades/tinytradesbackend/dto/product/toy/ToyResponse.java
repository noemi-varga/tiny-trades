package com.tinytrades.tinytradesbackend.dto.product.toy;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ToyResponse(
        Long id,
        Long traderId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        String gender,
        String condition,
        String ageGroup,
        String description,
        Set<String> tags,
        String status,
        String toyCategory,
        Set<String> imageLinks
) {
}
