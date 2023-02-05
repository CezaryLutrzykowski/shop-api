package com.shop.category.model;

import com.shop.product.model.Product;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<Product> products) {
}
