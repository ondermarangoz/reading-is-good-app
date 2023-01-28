package com.onder.readingisgood.service.order.model;

import com.onder.readingisgood.domain.entity.Book;
import com.onder.readingisgood.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderDTO {
    private Long OrderId;
    private Integer orderCount;
    private User user;
    private Book book;
}
