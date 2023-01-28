package com.onder.readingisgood.domain.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetOrderRequestModel {
    private Date startDate;
    private Date endDate;

}
