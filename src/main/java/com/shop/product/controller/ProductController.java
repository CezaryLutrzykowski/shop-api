package com.shop.product.controller;

import com.shop.product.model.Product;
import com.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public Page<Product> getProducts(@PageableDefault(size = 10) Pageable pageable) {
        return productService.getProducts(pageable);
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
