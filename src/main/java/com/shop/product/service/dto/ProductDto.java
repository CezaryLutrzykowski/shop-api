package com.shop.product.service.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDto(Long id,
                         String name,
                         Long categoryId,
                         String description,
                         String fullDescription,
                         BigDecimal price,
                         String currency,
                         String image,
                         String slug,
                         List<ReviewDto> reviews) {
}
