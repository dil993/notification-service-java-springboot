package com.sivalabs.bookstore.notifications.events;

import com.sivalabs.bookstore.notifications.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventHandler {
    private final NotificationService notificationService;

    @KafkaListener(topics = "${app.new-orders-topic}", groupId = "notifications")
    public void handle(OrderCreatedEvent event) {
        log.info("Received a OrderCreatedEvent with orderId:{}: ", event.getOrderId());
        notificationService.sendConfirmationNotification(event);
    }
}
