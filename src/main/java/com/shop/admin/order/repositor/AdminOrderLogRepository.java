package com.shop.admin.order.repositor;

import com.shop.admin.order.model.AdminOrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long> {
}
