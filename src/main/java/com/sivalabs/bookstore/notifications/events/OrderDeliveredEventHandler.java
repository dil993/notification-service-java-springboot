package com.sivalabs.bookstore.notifications.events;

import com.sivalabs.bookstore.notifications.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderDeliveredEventHandler {
    private final NotificationService notificationService;

    @KafkaListener(topics = "${app.delivered-orders-topic}", groupId = "notifications")
    public void handle(OrderDeliveredEvent event) {
        log.info("Received a OrderDeliveredEvent with orderId:{}: ", event.getOrderId());
        notificationService.sendDeliveredNotification(event);
    }
}
