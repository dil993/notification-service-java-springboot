package com.sivalabs.bookstore.notifications.events;

import com.sivalabs.bookstore.notifications.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCancelledEventHandler {
    private final NotificationService notificationService;

    @KafkaListener(topics = "${app.cancelled-orders-topic}", groupId = "notifications")
    public void handle(OrderCancelledEvent event) {
        log.info("Received a OrderCancelledEvent with orderId:{}: ", event.getOrderId());
        notificationService.sendCancelledNotification(event);
    }
}
