package com.shop.product.service.dto;

import lombok.Builder;

@Builder
public record ReviewDto(Long id,
                        Long productId,
                        String authorName,
                        String content,
                        boolean moderate) {
}
