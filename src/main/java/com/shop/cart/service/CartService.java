package com.shop.cart.service;

import com.shop.cart.model.dto.CartProductDto;
import com.shop.common.model.Cart;
import com.shop.common.model.CartItem;
import com.shop.common.model.Product;
import com.shop.common.repository.CartRepository;
import com.shop.common.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCard(Long id, CartProductDto cartProductDto) {
        Cart cart = getInitializedCard(id);
        cart.addProduct(CartItem.builder()
                .quantity(cartProductDto.quantity())
                .product(getProduct(cartProductDto.productId()))
                .cartId(cart.getId())
                .build());
        return cart;
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private Cart getInitializedCard(Long id) {
        if (id == null || id <= 0) {
            return cartRepository.save(Cart.builder().created(now()).build());
        }
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart updateCart(Long id, List<CartProductDto> cartProductDtos) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getItems().forEach(cartItem -> {
            cartProductDtos.stream()
                    .filter(cartProductDto -> cartItem.getProduct().getId().equals(cartProductDto.productId()))
                    .findFirst()
                    .ifPresent(cartProductDto -> cartItem.setQuantity(cartProductDto.quantity()));
        });
        return cart;
    }
}
