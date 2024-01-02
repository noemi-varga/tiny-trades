package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.model.image.ProductImage;
import com.tinytrades.tinytradesbackend.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }


}
