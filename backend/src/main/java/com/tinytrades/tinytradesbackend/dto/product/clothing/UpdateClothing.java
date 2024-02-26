package com.tinytrades.tinytradesbackend.dto.product.clothing;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UpdateClothing(
        @NotBlank(message = "Title must not be blank") String title,
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
