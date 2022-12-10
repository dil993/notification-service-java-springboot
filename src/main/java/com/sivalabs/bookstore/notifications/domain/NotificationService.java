package com.sivalabs.bookstore.notifications.domain;

import com.sivalabs.bookstore.notifications.events.model.OrderCancelledEvent;
import com.sivalabs.bookstore.notifications.events.model.OrderCreatedEvent;
import com.sivalabs.bookstore.notifications.events.model.OrderDeliveredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void sendConfirmationNotification(OrderCreatedEvent order) {
        String content =
                """
                Hi %s,
                This email is to notify you that your order : %s is received and will be processed soon.

                Thanks,
                BookStore Team
                """
                        .formatted(order.getCustomer().getName(), order.getOrderId());
        emailService.sendEmail(order.getCustomer().getEmail(), "Order Confirmation", content);
    }

    public void sendDeliveredNotification(OrderDeliveredEvent order) {
        String content =
                """
                Hi %s,
                This email is to notify you that your order : %s is delivered.

                Thanks,
                BookStore Team
                """
                        .formatted(order.getCustomer().getName(), order.getOrderId());
        emailService.sendEmail(
                order.getCustomer().getEmail(), "Order Delivery Confirmation", content);
    }

    public void sendCancelledNotification(OrderCancelledEvent order) {
        String content =
                """
                Hi %s,
                This email is to notify you that your order : %s is cancelled.

                Thanks,
                BookStore Team
                """
                        .formatted(order.getCustomer().getName(), order.getOrderId());
        emailService.sendEmail(
                order.getCustomer().getEmail(), "Order Cancellation Confirmation", content);
    }
}
