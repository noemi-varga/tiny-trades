package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {

    private final ClothingController clothingController;

    private final ToyController toyController;

    @Autowired
    public ProductController(ClothingController clothingController, ToyController toyController) {
        this.clothingController = clothingController;
        this.toyController = toyController;
    }
    @GetMapping("/products")
    public List<ProductResponse> findAllProduct() {
        List<ProductResponse> products = new ArrayList<>();
        products.addAll(clothingController.findAllClothing());
        products.addAll(toyController.findAllToys());
        return products;
    }

    @GetMapping("/products/{userId}")
    public List<ProductResponse> findAllProductByUser(@PathVariable Long userId) {
        List<ProductResponse> products = new ArrayList<>();
        products.addAll(clothingController.findAllClothingByUserId(userId));
        products.addAll(toyController.findAllToyByUserId(userId));
        return products;
    }

    @GetMapping("/products/search")
    public List<ProductResponse> searchProducts(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String ageGroup
    ) {
        List<ProductResponse> products = new ArrayList<>();
        products.addAll(clothingController.searchClothing(title, gender, ageGroup));
        //products.addAll(toyController.searchToy(titleFragment, gender));
        return products;
    }

}
