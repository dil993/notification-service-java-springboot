package com.sivalabs.bookstore.notifications.events;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.verify;

import com.sivalabs.bookstore.notifications.ApplicationProperties;
import com.sivalabs.bookstore.notifications.common.AbstractIntegrationTest;
import com.sivalabs.bookstore.notifications.events.model.Customer;
import com.sivalabs.bookstore.notifications.events.model.OrderCancelledEvent;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class OrderCancelledEventHandlerTest extends AbstractIntegrationTest {

    @Autowired private KafkaHelper kafkaHelper;

    @Autowired private ApplicationProperties properties;

    @Test
    void shouldHandleOrderCancelledEvent() {
        OrderCancelledEvent event = new OrderCancelledEvent();
        event.setOrderId(UUID.randomUUID().toString());
        event.setCustomer(new Customer());
        event.getCustomer().setName("Siva");
        event.getCustomer().setEmail("siva@gmail.com");
        log.info("Cancelling OrderId:{}", event.getOrderId());

        kafkaHelper.send(properties.cancelledOrdersTopic(), event);

        ArgumentCaptor<OrderCancelledEvent> captor =
                ArgumentCaptor.forClass(OrderCancelledEvent.class);
        await().atMost(30, SECONDS)
                .untilAsserted(
                        () -> {
                            verify(notificationService).sendCancelledNotification(captor.capture());
                            OrderCancelledEvent orderCancelledEvent = captor.getValue();
                            Assertions.assertThat(orderCancelledEvent.getOrderId())
                                    .isEqualTo(event.getOrderId());
                        });
    }
}
