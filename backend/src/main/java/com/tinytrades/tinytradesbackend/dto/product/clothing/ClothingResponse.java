package com.tinytrades.tinytradesbackend.dto.product.clothing;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ClothingResponse(
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
        String size,
        String color,
        String clothingCategory,
        Set<String> imageLinks
) {
}
