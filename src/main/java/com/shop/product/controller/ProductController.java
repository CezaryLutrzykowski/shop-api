package com.shop.product.controller;

import com.shop.product.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                new Product("Nowy product 1","Nowy kategoria 1","Nowy opis 1",new BigDecimal("8.99"),"PLN"),
                new Product("Nowy product 2","Nowy kategoria 2","Nowy opis 2",new BigDecimal("8.99"),"PLN"),
                new Product("Nowy product 3","Nowy kategoria 3","Nowy opis 3",new BigDecimal("8.99"),"PLN"),
                new Product("Nowy product 4","Nowy kategoria 4","Nowy opis 4",new BigDecimal("8.99"),"PLN")
        );
    }
}
