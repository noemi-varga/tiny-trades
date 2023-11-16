package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.clothing.ClothingResponse;
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
    public List<ClothingResponse> findAllClothing() {
        return clothingService.findAllClothing();
    }

//    @GetMapping("/clothing/{id}")
//    public ClothingResponse findClothingById(@PathVariable Long id) {
//        return clothingService.findClothingResponseById(id);
//    }
//
//    @PostMapping("/clothing/")
//    public ClothingResponse addClothing(@RequestBody NewClothingRequest newClothingRequest) {
//        return clothingService.addClothing(newClothingRequest);
//    }
//
//    @PutMapping("/clothing/{id}")
//    public ResponseEntity<?> updateClothingById(@PathVariable Long id, @RequestBody UpdateClothingRequest updateClothingRequest) {
//        ClothingResponse clothingResponse = clothingService.updateClothingById(id, updateClothingRequest);
//        return new ResponseEntity<>(clothingResponse, HttpStatus.CREATED);
//    }


}
