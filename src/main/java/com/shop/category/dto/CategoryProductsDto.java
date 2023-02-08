package com.shop.category.dto;

import com.shop.common.dto.ProductListDto;
import com.shop.common.model.Category;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
