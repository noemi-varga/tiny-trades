package com.tinytrades.tinytradesbackend.dto.product.clothing;

import lombok.Builder;

@Builder
public record ClothingResponse(Long id, String title) {
}
