package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    private final ProductService productService;

    private final ClothingController clothingController;


    @Autowired
    public ProductController(ProductService productService, ClothingController clothingController) {
        this.productService = productService;
        this.clothingController = clothingController;
    }
    @GetMapping("/products")
    public List<ProductResponse> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/products/{userId}")
    public List<ProductResponse> findAllProductsByUser(@PathVariable Long userId) {
        return productService.findAllProductsByUserId(userId);
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
