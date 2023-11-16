package com.tinytrades.tinytradesbackend.dto.product.toy;

import lombok.Builder;

@Builder
public record ToyResponse(Long id, String title) {
}
