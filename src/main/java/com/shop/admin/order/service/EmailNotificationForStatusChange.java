package com.shop.admin.order.service;

import com.shop.admin.order.model.AdminOrder;
import com.shop.admin.order.model.AdminOrderStatus;
import com.shop.common.mail.EmailClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.shop.admin.order.service.AdminOrderEmailMessage.createCompletedEmailMessage;
import static com.shop.admin.order.service.AdminOrderEmailMessage.createProcessingEmailMessage;
import static com.shop.admin.order.service.AdminOrderEmailMessage.createRefundEmailMessage;

@Service
@RequiredArgsConstructor
class EmailNotificationForStatusChange {

    private final EmailClientService emailClientService;

    void sendEmailNotification(AdminOrderStatus newStatus, AdminOrder adminOrder) {
        if(newStatus == AdminOrderStatus.PROCESSING){
            sendEmail(adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + "  zmieniło status na: " + newStatus.getValue(),
                    createProcessingEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus == AdminOrderStatus.COMPLETED){
            sendEmail(adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + " zostało zrealizowane",
                    createCompletedEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus == AdminOrderStatus.REFUND){
            sendEmail(
                    adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + " zostało zwrócone",
                    createRefundEmailMessage(adminOrder.getId(), newStatus));
        }
    }

    private void sendEmail(String email, String subject, String message) {
        emailClientService.getInstance().send(email, subject, message);
    }
}
