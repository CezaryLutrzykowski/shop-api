package com.shop.order.controller;

import com.shop.order.model.dto.InitOrder;
import com.shop.order.model.dto.OrderDto;
import com.shop.order.model.dto.OrderSummary;
import com.shop.order.service.OrderService;
import com.shop.order.service.PaymentService;
import com.shop.order.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/initData")
    public InitOrder initData() {
        return InitOrder.builder()
                .shipment(shipmentService.getShipments())
                .payment(paymentService.getPayments())
                .build();
    }
}
