package com.tinytrades.tinytradesbackend.dto.product.clothing;
import java.util.Set;

public record NewClothing(
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
