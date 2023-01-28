package com.onder.readingisgood.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderModel {
    private Long bookId;
    @Valid
    @Positive
    private Integer quantity;
}
