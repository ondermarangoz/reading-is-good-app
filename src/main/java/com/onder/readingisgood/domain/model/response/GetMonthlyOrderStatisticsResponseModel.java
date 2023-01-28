package com.onder.readingisgood.domain.model.response;

import com.onder.readingisgood.domain.model.dto.MonthlyOrderStatisticsModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetMonthlyOrderStatisticsResponseModel {
    private List<MonthlyOrderStatisticsModel> monthlyOrderStatisticsList;
}
