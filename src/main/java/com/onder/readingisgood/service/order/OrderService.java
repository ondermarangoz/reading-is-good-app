package com.onder.readingisgood.service.order;

import com.onder.readingisgood.service.order.model.OrderDTO;
import com.onder.readingisgood.service.order.request.RequestGetOrders;

import java.util.List;

public interface OrderService {
    void orderBooks(List<OrderDTO> orderDTOList);

    ResponseGetOrders getOrders(RequestGetOrders requestGetOrders);

    OrderDTO getOrderById(Long id);

}
