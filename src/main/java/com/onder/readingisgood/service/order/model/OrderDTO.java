package com.onder.readingisgood.service.order.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class OrderDTO {

    private long bookId;
    private int quantity;
}
