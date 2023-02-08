package com.shop.product.controller;

import com.shop.common.dto.ProductListDto;
import com.shop.common.model.Product;
import com.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public Page<ProductListDto> getProducts(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productService.getProducts(pageable);
        List<ProductListDto> productListDtos = products.getContent().stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .currency(product.getCurrency())
                        .image(product.getImage())
                        .slug(product.getSlug())
                        .build())
                .toList();

        return new PageImpl<>(productListDtos, pageable, products.getTotalElements());
    }

    @GetMapping("/products/{slug}")
    public Product getProduct(
            @Pattern(regexp = "[a-z0-9\\-]+")
            @Length(max = 255)
            @PathVariable
            String slug) {
        return productService.getProductBySlug(slug);
    }
}
