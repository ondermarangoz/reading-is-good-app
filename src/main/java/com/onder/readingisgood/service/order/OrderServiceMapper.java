package com.onder.readingisgood.service.order;

import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.service.order.model.GetOrderDTO;
import com.onder.readingisgood.service.order.model.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderServiceMapper {
    

    OrderDTO mapOrderToOrderDto(Order order);
    List<GetOrderDTO> mapOrderListToOrderDtoList(List<Order> orders);
}
