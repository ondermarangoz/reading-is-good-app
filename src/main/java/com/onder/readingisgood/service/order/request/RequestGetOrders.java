package com.onder.readingisgood.service.order.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestGetOrders {
    private Date startDate;
    private Date endDate;
}
