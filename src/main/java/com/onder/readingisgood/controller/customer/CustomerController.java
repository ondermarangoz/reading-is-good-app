package com.onder.readingisgood.controller.customer;

import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.mapper.CustomerMapper;
import com.onder.readingisgood.domain.model.dto.OrderModel;
import com.onder.readingisgood.domain.model.dto.UserModel;
import com.onder.readingisgood.domain.model.request.GetCustomerOrderRequestModel;
import com.onder.readingisgood.domain.model.request.GetCustomerRequestModel;
import com.onder.readingisgood.service.customer.CustomerService;
import com.onder.readingisgood.service.customer.request.RequestGetCustomer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);


    @PostMapping("/getCustomer")
    public ResponseEntity<UserModel> getCustomer(@RequestBody GetCustomerRequestModel request) {
        RequestGetCustomer requestGetCustomer = mapper.customerRequestModelToCustomerRequest(request);
        UserModel user = mapper.mapUserDetailToUserDTO(customerService.getCustomer(requestGetCustomer));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/getCustomerOrder")
    public ResponseEntity<List<OrderModel>> getCustomerOrders(@RequestBody GetCustomerOrderRequestModel request) {
        List<Order> orderList = customerService.getOrderOfCustomer(mapper.mapOrderRequestModelToOrderRequest(request));
        List<OrderModel> orderModelList = mapper.mapOrderToOrderModel(orderList);
        return new ResponseEntity<>(orderModelList, HttpStatus.OK);
    }


}
