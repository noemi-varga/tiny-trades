package com.tinytrades.tinytradesbackend.dto.product.toy;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record NewToy(
        @NotBlank(message = "Title must not be blank") String title,
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
