package com.sivalabs.bookstore.notifications.events;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.verify;

import com.sivalabs.bookstore.notifications.ApplicationProperties;
import com.sivalabs.bookstore.notifications.common.AbstractIntegrationTest;
import com.sivalabs.bookstore.notifications.events.model.Customer;
import com.sivalabs.bookstore.notifications.events.model.OrderDeliveredEvent;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class OrderDeliveredEventHandlerTest extends AbstractIntegrationTest {

    @Autowired private KafkaHelper kafkaHelper;

    @Autowired private ApplicationProperties properties;

    @Test
    void shouldHandleOrderDeliveredEvent() {
        OrderDeliveredEvent event = new OrderDeliveredEvent();
        event.setOrderId(UUID.randomUUID().toString());
        event.setCustomer(new Customer());
        event.getCustomer().setName("Siva");
        event.getCustomer().setEmail("siva@gmail.com");
        log.info("Delivered OrderId:{}", event.getOrderId());

        kafkaHelper.send(properties.deliveredOrdersTopic(), event);
        ArgumentCaptor<OrderDeliveredEvent> captor =
                ArgumentCaptor.forClass(OrderDeliveredEvent.class);

        await().atMost(30, SECONDS)
                .untilAsserted(
                        () -> {
                            verify(notificationService).sendDeliveredNotification(captor.capture());
                            OrderDeliveredEvent orderDeliveredEvent = captor.getValue();
                            Assertions.assertThat(orderDeliveredEvent.getOrderId())
                                    .isEqualTo(event.getOrderId());
                        });
    }
}
