package com.onder.readingisgood.domain.model.response;

import com.onder.readingisgood.domain.model.dto.GetOrderModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetOrderResponseModel {
    private List<GetOrderModel> getOrderList;
 }
