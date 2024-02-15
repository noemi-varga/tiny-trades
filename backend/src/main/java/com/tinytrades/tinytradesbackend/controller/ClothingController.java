package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.NewClothing;
import com.tinytrades.tinytradesbackend.dto.product.clothing.UpdateClothing;
import com.tinytrades.tinytradesbackend.repository.specifications.ClothingSearchCriteria;
import com.tinytrades.tinytradesbackend.service.ClothingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clothing")
@RequiredArgsConstructor
public class ClothingController {
    private final ClothingService clothingService;

    @GetMapping()
    public List<ProductResponse> findAllClothing() {
        return clothingService.findAllClothing();
    }

    @GetMapping("/{id}")
    public ClothingResponse findClothingById(@PathVariable Long id) {
        return clothingService.findClothingById(id);
    }

    @GetMapping("/users/{userId}")
    public List<ProductResponse> findAllClothingByUserId(@PathVariable Long userId) {
        return clothingService.findAllClothingByUserId(userId);
    }

    @PostMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addNewClothing(@PathVariable Long userId, @RequestBody NewClothing newClothing){
        return clothingService.addNewClothing(userId, newClothing);
    }

    @PutMapping("/{clothingId}/users/{userId}/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse updateClothing(@PathVariable Long userId, @PathVariable Long clothingId, @RequestBody UpdateClothing updateClothing){
        return clothingService.updateClothing(userId, clothingId, updateClothing);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchClothing(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String ageGroup
    ) {
        ClothingSearchCriteria criteria = ClothingSearchCriteria.builder()
                .titleFragment(title)
                .gender(gender)
                .ageGroup(ageGroup)
                .build();
        return clothingService.searchClothing(criteria);
    }

}
