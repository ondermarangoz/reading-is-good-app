package com.onder.readingisgood.controller.order;

import com.onder.readingisgood.domain.mapper.OrderMapper;
import com.onder.readingisgood.domain.model.dto.OrderModel;
import com.onder.readingisgood.domain.model.request.GetOrderRequestModel;
import com.onder.readingisgood.domain.model.response.GetOrderResponseModel;
import com.onder.readingisgood.service.order.OrderService;
import com.onder.readingisgood.service.order.model.OrderDTO;
import com.onder.readingisgood.service.order.request.RequestGetOrders;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/orderBooks")
    public void orderBooks(HttpServletRequest request, @Valid @RequestBody List<OrderModel> orderModelList) {
        List<OrderDTO> orderDTOList = mapper.mapOrderModelToOrderList(orderModelList);
        orderService.orderBooks(orderDTOList);
    }
    @PostMapping("/getOrders")
    public GetOrderResponseModel getOrders(@Validated @RequestBody GetOrderRequestModel requestModel){
        RequestGetOrders requestGetOrders = mapper.mapGetOrderRequestModelToOrderRequest(requestModel);
        return mapper.mapGetOrderToGetOrderResponse(orderService.getOrders(requestGetOrders));

    }

    @GetMapping("/book/{id}")
    public OrderDTO getBookById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }



}
