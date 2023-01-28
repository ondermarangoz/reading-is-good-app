package com.onder.readingisgood.service.customer.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestGetCustomer {
    private String email;
}
