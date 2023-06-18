package com.shop.order.model.dto;

import com.shop.order.model.Payment;
import com.shop.order.model.Shipment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InitOrder {
    private List<Shipment> shipment;
    private List<Payment> payment;
}
