package com.shop.category.repository;

import com.shop.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
/*    @Query("select c from Category c " +
            "left join fetch c.product " +
            "where c.slug=:slug")*/
    Category findBySlug(String slug);
}
