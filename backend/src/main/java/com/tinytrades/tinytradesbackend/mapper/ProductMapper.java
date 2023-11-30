package com.tinytrades.tinytradesbackend.mapper;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.product.ProductImage;
import com.tinytrades.tinytradesbackend.model.product.Toy;

import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Clothing clothing) {
        return ProductResponse
                .builder()
                .id(clothing.getId())
                .title(clothing.getTitle())
                .productType(clothing.getClass().getSimpleName())
                .firstImageLink(clothing.getFirstImageLink())
                .build();
    }

    public static ProductResponse mapToProductResponse(Toy toy) {
        return ProductResponse
                .builder()
                .id(toy.getId())
                .title(toy.getTitle())
                .productType(toy.getClass().getSimpleName())
                .firstImageLink(toy.getFirstImageLink())
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
                .imageLinks(clothing.getProductImages().stream().map(ProductImage::getUrl).collect(Collectors.toSet()))
                .build();
    }

    public static ToyResponse mapToToyResponse(Toy toy) {
        return ToyResponse.builder()
                .id(toy.getId())
                .traderId(toy.getTrader().getId())
                .createdAt(toy.getCreatedAt())
                .updatedAt(toy.getUpdatedAt())
                .title(toy.getTitle())
                .gender(toy.getGender().name())
                .condition(toy.getCondition().name())
                .ageGroup(toy.getAgeGroup().name())
                .description(toy.getDescription())
                .tags(toy.getTags())
                .status(toy.getStatus().name())
                .toyCategory(toy.getToyCategory().name())
                .imageLinks(toy.getProductImages().stream().map(ProductImage::getUrl).collect(Collectors.toSet()))
                .build();
    }
}
