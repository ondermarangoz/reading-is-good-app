package com.onder.readingisgood.domain.mapper;

import com.onder.readingisgood.domain.model.dto.OrderModel;
import com.onder.readingisgood.domain.model.request.GetOrderRequestModel;
import com.onder.readingisgood.domain.model.response.GetOrderResponseModel;
import com.onder.readingisgood.service.order.ResponseGetOrders;
import com.onder.readingisgood.service.order.model.OrderDTO;
import com.onder.readingisgood.service.order.request.RequestGetOrders;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderDTO> mapOrderModelToOrderList(List<OrderModel> orderModelList);

    RequestGetOrders mapGetOrderRequestModelToOrderRequest(GetOrderRequestModel requestModel);

    GetOrderResponseModel mapGetOrderToGetOrderResponse(ResponseGetOrders orders);
}
