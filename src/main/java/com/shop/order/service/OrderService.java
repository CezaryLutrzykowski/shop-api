package com.shop.order.service;

import com.shop.common.model.Cart;
import com.shop.common.model.CartItem;
import com.shop.common.repository.CartItemRepository;
import com.shop.common.repository.CartRepository;
import com.shop.order.model.Order;
import com.shop.order.model.OrderRow;
import com.shop.order.model.OrderStatus;
import com.shop.order.model.dto.OrderDto;
import com.shop.order.model.dto.OrderSummary;
import com.shop.order.repository.OrderRepository;
import com.shop.order.repository.OrderRowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Order order = Order.builder()
                .firstname(orderDto.getFirstname())
                .lastname(orderDto.getLastname())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems()))
//                .orderRows()
                .build();

        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId());

        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());

        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .build();
    }

    private BigDecimal calculateGrossValue(List<CartItem> items) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private void saveOrderRows(Cart cart, Long id) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(id)
                        .build()
                )
                .peek(orderRowRepository::save)
                .toList();
    }
}
