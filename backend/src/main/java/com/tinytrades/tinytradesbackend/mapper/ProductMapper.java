package com.tinytrades.tinytradesbackend.mapper;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.product.Toy;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Clothing clothing) {
        return ProductResponse
                .builder()
                .id(clothing.getId())
                .title(clothing.getTitle())
                .productType(clothing.getClass().getSimpleName())
                .build();
    }

    public static ProductResponse mapToProductResponse(Toy toy) {
        return ProductResponse
                .builder()
                .id(toy.getId())
                .title(toy.getTitle())
                .productType(toy.getClass().getSimpleName())
                .build();
    }

    public static ClothingResponse mapToClothingResponse(Clothing clothing) {
        return ClothingResponse.builder()
                .id(clothing.getId())
                .traderId(clothing.getTrader().getId())
                .createdAt(clothing.getCreatedAt())
                .updatedAt(clothing.getUpdatedAt())
                .title(clothing.getTitle())
                .gender(clothing.getGender().name())
                .condition(clothing.getCondition().name())
                .ageGroup(clothing.getAgeGroup().name())
                .description(clothing.getDescription())
                .tags(clothing.getTags())
                .status(clothing.getStatus().name())
                .size(clothing.getSize().name())
                .color(clothing.getColor().name())
                .clothingCategory(clothing.getClothingCategory().name())
                .build();
    }

}
