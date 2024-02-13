package com.tinytrades.tinytradesbackend.mapper;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.NewClothing;
import com.tinytrades.tinytradesbackend.dto.product.clothing.UpdateClothing;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.UpdateToy;
import com.tinytrades.tinytradesbackend.model.enums.*;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.product.Product;
import com.tinytrades.tinytradesbackend.model.image.ProductImage;
import com.tinytrades.tinytradesbackend.model.product.Toy;
import com.tinytrades.tinytradesbackend.model.user.User;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse
                .builder()
                .id(product.getId())
                .title(product.getTitle())
                .productType(product.getClass().getSimpleName())
                .firstImageLink(product.getFirstImageLink())
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

    public static Clothing mapNewClothingToClothing(NewClothing newClothing, User user) {
        return Clothing.builder()
                .trader(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title(newClothing.title())
                .gender(Gender.valueOf(newClothing.gender()))
                .condition(ConditionType.valueOf(newClothing.condition()))
                .ageGroup(AgeGroup.valueOf(newClothing.ageGroup()))
                .description(newClothing.description())
                .tags(newClothing.tags())
                .status(Status.ACTIVE)
                .size(ClothingSize.valueOf(newClothing.size()))
                .color(ClothingColor.valueOf(newClothing.color()))
                .clothingCategory(ClothingCategory.valueOf(newClothing.clothingCategory()))
                .build();
    }

    public static void setUpdateClothingToClothing(UpdateClothing updateClothing, Clothing clothing) {
        clothing.setUpdatedAt(LocalDateTime.now());
        clothing.setTitle(updateClothing.title());
        clothing.setGender(Gender.valueOf(updateClothing.gender()));
        clothing.setCondition(ConditionType.valueOf(updateClothing.condition()));
        clothing.setAgeGroup(AgeGroup.valueOf(updateClothing.ageGroup()));
        clothing.setDescription(updateClothing.description());
        clothing.setTags(updateClothing.tags());
        clothing.setStatus(Status.valueOf(updateClothing.status()));
        clothing.setSize(ClothingSize.valueOf(updateClothing.size()));
        clothing.setColor(ClothingColor.valueOf(updateClothing.color()));
        clothing.setClothingCategory(ClothingCategory.valueOf(updateClothing.clothingCategory()));
    }

    public static Toy mapNewToyToToy(NewToy newToy, User user) {
        return Toy.builder()
                .trader(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title(newToy.title())
                .gender(Gender.valueOf(newToy.gender()))
                .condition(ConditionType.valueOf(newToy.condition()))
                .ageGroup(AgeGroup.valueOf(newToy.ageGroup()))
                .description(newToy.description())
                .tags(newToy.tags())
                .status(Status.ACTIVE)
                .toyCategory(ToyCategory.valueOf(newToy.toyCategory()))
                .build();
    }

    public static void setUpdateToyToToy(UpdateToy updateToy, Toy toy) {
        toy.setUpdatedAt(LocalDateTime.now());
        toy.setTitle(updateToy.title());
        toy.setGender(Gender.valueOf(updateToy.gender()));
        toy.setCondition(ConditionType.valueOf(updateToy.condition()));
        toy.setAgeGroup(AgeGroup.valueOf(updateToy.ageGroup()));
        toy.setDescription(updateToy.description());
        toy.setTags(updateToy.tags());
        toy.setStatus(Status.valueOf(updateToy.status()));
        toy.setToyCategory(ToyCategory.valueOf(updateToy.toyCategory()));
    }
}
