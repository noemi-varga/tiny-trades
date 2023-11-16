package com.tinytrades.tinytradesbackend.mapper;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.product.Toy;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Clothing clothing) {
        return ProductResponse
                .builder()
                .id(clothing.getId())
                .title(clothing.getTitle())
                .build();
    }
    public static ProductResponse mapToProductResponse(Toy toy) {
        return ProductResponse
                .builder()
                .id(toy.getId())
                .title(toy.getTitle())
                .build();
    }
}
