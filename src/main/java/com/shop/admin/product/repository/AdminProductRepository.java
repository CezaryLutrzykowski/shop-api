package com.shop.admin.product.repository;

import com.shop.admin.product.model.AdminProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
