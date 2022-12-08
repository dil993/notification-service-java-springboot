package com.sivalabs.bookstore.notifications.events;

import com.sivalabs.bookstore.notifications.ApplicationProperties;
import com.sivalabs.bookstore.notifications.common.AbstractIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@Slf4j
class OrderCreatedEventHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    private ApplicationProperties properties;

    @Test
    void shouldHandleOrderCreatedEvent() {
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(UUID.randomUUID().toString());
        event.setCustomer(new Customer());
        event.getCustomer().setName("Siva");
        log.info("Delivered OrderId:{}", event.getOrderId());

        kafkaTemplate.send(properties.newOrdersTopic(), event);

        await().atMost(30, SECONDS).untilAsserted(() -> {
            verify(notificationService).sendConfirmationNotification(any(OrderCreatedEvent.class));
        });

    }
}