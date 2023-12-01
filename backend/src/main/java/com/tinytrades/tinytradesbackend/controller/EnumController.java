package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.model.enums.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enums")
public class EnumController {

    @GetMapping("/ageGroup")
    public List<String> getAgeGroups() {
        return Arrays.stream(AgeGroup.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/clothingCategory")
    public List<String> getClothingCategories() {
        return Arrays.stream(ClothingCategory.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/clothingColor")
    public List<String> getClothingColors() {
        return Arrays.stream(ClothingColor.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/clothingSize")
    public List<String> getClothingSizes() {
        return Arrays.stream(ClothingSize.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/conditionType")
    public List<String> getConditionTypes() {
        return Arrays.stream(ConditionType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/gender")
    public List<String> getGenders() {
        return Arrays.stream(Gender.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/status")
    public List<String> getStatuses() {
        return Arrays.stream(Status.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/toyCategory")
    public List<String> getToyCategories() {
        return Arrays.stream(ToyCategory.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}