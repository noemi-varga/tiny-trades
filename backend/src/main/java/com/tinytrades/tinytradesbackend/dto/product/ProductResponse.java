package com.tinytrades.tinytradesbackend.dto.product;

import lombok.Builder;

@Builder
public record ProductResponse(Long id, String title, String productType, String firstImageLink) {
}
