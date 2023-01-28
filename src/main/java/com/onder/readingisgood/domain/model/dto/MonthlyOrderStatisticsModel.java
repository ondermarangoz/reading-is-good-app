package com.onder.readingisgood.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyOrderStatisticsModel {
    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private Float totalPurchasedAmount;
}
