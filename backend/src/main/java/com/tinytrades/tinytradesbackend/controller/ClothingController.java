package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.NewClothing;
import com.tinytrades.tinytradesbackend.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClothingController {
    private final ClothingService clothingService;

    @Autowired
    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @GetMapping("/clothing/")
    public List<ProductResponse> findAllClothing() {
        return clothingService.findAllClothing();
    }

    @PostMapping("/users/{userId}/clothing/")
    public ProductResponse addNewClothing(@PathVariable Long userId, @RequestBody NewClothing newClothing){
        return clothingService.addNewClothing(userId, newClothing);
    }



}
