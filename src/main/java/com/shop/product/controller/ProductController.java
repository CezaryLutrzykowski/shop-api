package com.shop.product.controller;

import com.shop.product.model.Product;
import com.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public Page<Product> getProducts(@PageableDefault(size = 10) Pageable pageable) {
        return productService.getProduct(pageable);
    }
}
