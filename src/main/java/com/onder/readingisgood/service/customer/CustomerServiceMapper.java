package com.onder.readingisgood.service.customer;

import com.onder.readingisgood.service.customer.request.RequestGetCustomerOrder;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerServiceMapper {
    String mapRequestCustomerOrderToEmail(RequestGetCustomerOrder requestGetCustomerOrder);
}
