package com.tinytrades.tinytradesbackend.dto.product.toy;

import java.util.Set;

public record NewToy(
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
