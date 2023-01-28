package com.onder.readingisgood.service.customer;

import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.service.customer.request.RequestGetCustomer;
import com.onder.readingisgood.service.customer.request.RequestGetCustomerOrder;

import java.util.List;

public interface CustomerService {
    User signUpUser(User user);

    User getCustomer(RequestGetCustomer requestGetCustomer);
    List<Order> getOrderOfCustomer(RequestGetCustomerOrder requestGetCustomerOrder);

}
