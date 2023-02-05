package com.shop.category.service;

import com.shop.category.model.Category;
import com.shop.category.model.CategoryProductsDto;
import com.shop.category.repository.CategoryRepository;
import com.shop.product.model.Product;
import com.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryProductsDto getCategoriesWithProducts(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug);
        Page<Product> page =  productRepository.findByCategoryId(category.getId(), pageable);

        return new CategoryProductsDto(category,page);
    }
}
