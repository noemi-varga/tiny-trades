package com.tinytrades.tinytradesbackend.dto.clothing;

import lombok.Builder;

@Builder
public record ClothingResponse(Long id, String title) {
}
