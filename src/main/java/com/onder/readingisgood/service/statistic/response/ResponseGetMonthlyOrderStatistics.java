package com.onder.readingisgood.service.statistic.response;

import com.onder.readingisgood.service.statistic.model.MonthlyOrderStatistics;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseGetMonthlyOrderStatistics {
    private List<MonthlyOrderStatistics> monthlyOrderStatisticsList;

}
