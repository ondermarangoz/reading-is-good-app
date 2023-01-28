package com.onder.readingisgood.service.statistic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyOrderStatistics {
    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private Float totalPurchasedAmount;
}
