package com.sivalabs.bookstore.notifications.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private String orderId;
    private Set<LineItem> items;
    private Customer customer;
    private Address deliveryAddress;
}
