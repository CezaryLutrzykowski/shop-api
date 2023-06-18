package com.shop.order.service;

import com.shop.common.mail.EmailClientService;
import com.shop.common.model.Cart;
import com.shop.common.repository.CartItemRepository;
import com.shop.common.repository.CartRepository;
import com.shop.order.model.Order;
import com.shop.order.model.Payment;
import com.shop.order.model.Shipment;
import com.shop.order.model.dto.OrderDto;
import com.shop.order.model.dto.OrderSummary;
import com.shop.order.repository.OrderRepository;
import com.shop.order.repository.OrderRowRepository;
import com.shop.order.repository.PaymentRepository;
import com.shop.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shop.order.service.mapper.OrderEmailMessageMapper.createEmailMessage;
import static com.shop.order.service.mapper.OrderMapper.createNewOrder;
import static com.shop.order.service.mapper.OrderMapper.createOrderSummary;
import static com.shop.order.service.mapper.OrderMapper.mapToOrderRow;
import static com.shop.order.service.mapper.OrderMapper.mapToOrderRowWithQuantity;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRowRepository orderRowRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        sendConfirmEmail(newOrder);
        return createOrderSummary(payment, newOrder);
    }

    private void sendConfirmEmail(Order newOrder) {
        emailClientService.getInstance()
                .send(newOrder.getEmail(),
                        "Twoje zamówienie zostało przyjęte",
                        createEmailMessage(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem)
                )
                .peek(orderRowRepository::save)
                .toList();
    }
}
