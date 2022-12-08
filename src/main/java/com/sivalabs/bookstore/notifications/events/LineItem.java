package com.sivalabs.bookstore.notifications.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
    private String isbn;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}