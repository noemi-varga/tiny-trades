package com.tinytrades.tinytradesbackend.mapper;

import com.tinytrades.tinytradesbackend.dto.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.model.product.Clothing;

public class ClothingMapper {

    public static ClothingResponse mapToUserResponse(Clothing clothing) {
        return ClothingResponse
                .builder()
                .id(clothing.getId())
                .title(clothing.getTitle())
                .build();
    }
}
