package com.onder.readingisgood.service.statistic;

import com.onder.readingisgood.service.statistic.request.RequestGetMonthlyOrderStatistics;
import com.onder.readingisgood.service.statistic.response.ResponseGetMonthlyOrderStatistics;

public interface StatisticService {

    Integer totalOrderCount(int month);
    Float totalPurchaseAmount(int month);
    Integer totalAmountOfPurchasedBooks(int month);

    ResponseGetMonthlyOrderStatistics getMonthlyOrderStatistics(RequestGetMonthlyOrderStatistics request);

}
