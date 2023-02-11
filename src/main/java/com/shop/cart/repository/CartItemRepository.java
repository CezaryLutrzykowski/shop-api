package com.shop.cart.repository;

import com.shop.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    Long countByCartId(Long cartId);
}
