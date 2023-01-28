package com.onder.readingisgood.domain.mapper;

import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.model.dto.OrderModel;
import com.onder.readingisgood.domain.model.dto.UserModel;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.model.request.GetCustomerOrderRequestModel;
import com.onder.readingisgood.domain.model.request.GetCustomerRequestModel;
import com.onder.readingisgood.service.customer.request.RequestGetCustomer;
import com.onder.readingisgood.service.customer.request.RequestGetCustomerOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    User mapToUser(UserModel user);
    UserModel mapToUserDTO(User createdUser);

    UserModel mapUserDetailToUserDTO(User customer);

    RequestGetCustomer customerRequestModelToCustomerRequest(GetCustomerRequestModel request);

    RequestGetCustomerOrder mapOrderRequestModelToOrderRequest(GetCustomerOrderRequestModel request);

    List<OrderModel> mapOrderToOrderModel(List<Order> orderList);
}
