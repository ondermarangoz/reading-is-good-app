package com.onder.readingisgood.service.order;

import com.onder.readingisgood.service.order.model.GetOrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseGetOrders {
    private List<GetOrderDTO> getOrderList;
}
