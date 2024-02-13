package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ClothingController clothingController;

    @GetMapping()
    public List<ProductResponse> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{userId}")
    public List<ProductResponse> findAllProductsByUser(@PathVariable Long userId) {
        return productService.findAllProductsByUserId(userId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProducts(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String ageGroup
    ) {
        return clothingController.searchClothing(title, gender, ageGroup);
    }

}
