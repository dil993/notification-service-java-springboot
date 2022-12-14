package com.sivalabs.bookstore.notifications.events.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelledEvent {
    private String orderId;
    private String reason;
    private Set<LineItem> items;
    private Customer customer;
    private Address deliveryAddress;
}
