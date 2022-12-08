package com.sivalabs.bookstore.notifications.domain;

import com.sivalabs.bookstore.notifications.events.OrderCancelledEvent;
import com.sivalabs.bookstore.notifications.events.OrderCreatedEvent;
import com.sivalabs.bookstore.notifications.events.OrderDeliveredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendConfirmationNotification(OrderCreatedEvent order) {
        String email = """
                Hi %s,
                This email is to notify you that your order : %s is received and will be processed soon.
                
                Thanks,
                BookStore Team
                """.formatted(order.getCustomer().getName(), order.getOrderId());
        log.info("==========================================================");
        log.info("                    Order Confirmation           ");
        log.info("==========================================================");
        log.info(email);
        log.info("==========================================================");
    }

    public void sendDeliveredNotification(OrderDeliveredEvent order) {
        String email = """
                Hi %s,
                This email is to notify you that your order : %s is delivered.
                
                Thanks,
                BookStore Team
                """.formatted(order.getCustomer().getName(), order.getOrderId());
        log.info("==========================================================");
        log.info("                    Order Delivery Confirmation           ");
        log.info("==========================================================");
        log.info(email);
        log.info("==========================================================");
    }

    public void sendCancelledNotification(OrderCancelledEvent order) {
        String email = """
                Hi %s,
                This email is to notify you that your order : %s is cancelled.
                
                Thanks,
                BookStore Team
                """.formatted(order.getCustomer().getName(), order.getOrderId());
        log.info("==========================================================");
        log.info("                    Order Cancellation Notification       ");
        log.info("==========================================================");
        log.info(email);
        log.info("==========================================================");
    }


}
