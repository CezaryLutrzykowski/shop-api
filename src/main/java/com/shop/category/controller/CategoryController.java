package com.shop.category.controller;


import com.shop.category.dto.CategoryProductsDto;
import com.shop.category.service.CategoryService;
import com.shop.common.model.Category;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{slug}/products")
    public CategoryProductsDto getCategoryWithProducts(@PathVariable
                                            @Pattern(regexp = "[a-z0-9\\-]+")
                                            @Length(max = 255) String slug, Pageable pageable) {

        return categoryService.getCategoriesWithProducts(slug,pageable);
    }
}
