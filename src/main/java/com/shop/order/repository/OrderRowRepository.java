package com.shop.order.repository;

import com.shop.order.model.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
