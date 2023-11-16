package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ProductController {

    private final ClothingController clothingController;

    private final ToyController toyController;

    @Autowired
    public ProductController(ClothingController clothingController, ToyController toyController) {
        this.clothingController = clothingController;
        this.toyController = toyController;
    }

    @GetMapping("/product/")
    public List<ProductResponse> findAllProduct() {
        List<ProductResponse> products = new ArrayList<>();
        products.addAll(clothingController.findAllClothing());
        products.addAll(toyController.findAllToys());
        return products;
    }

}
