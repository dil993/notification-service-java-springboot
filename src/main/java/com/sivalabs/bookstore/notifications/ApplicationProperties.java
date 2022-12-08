package com.sivalabs.bookstore.notifications;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record ApplicationProperties(
        String newOrdersTopic,
        String deliveredOrdersTopic,
        String cancelledOrdersTopic
) {
}