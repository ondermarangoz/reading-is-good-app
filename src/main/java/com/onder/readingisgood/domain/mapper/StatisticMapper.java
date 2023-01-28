package com.onder.readingisgood.domain.mapper;

import com.onder.readingisgood.domain.model.request.GetMonthlyOrderStatisticsRequestModel;
import com.onder.readingisgood.domain.model.response.GetMonthlyOrderStatisticsResponseModel;
import com.onder.readingisgood.service.statistic.request.RequestGetMonthlyOrderStatistics;
import com.onder.readingisgood.service.statistic.response.ResponseGetMonthlyOrderStatistics;
import org.mapstruct.Mapper;

@Mapper
public interface StatisticMapper {

    RequestGetMonthlyOrderStatistics mapStatisticsRequestModelToStatisticsRequest(GetMonthlyOrderStatisticsRequestModel requestModel);

    GetMonthlyOrderStatisticsResponseModel mapStatisticsResponseToStatisticsResponseModel(ResponseGetMonthlyOrderStatistics serviceResponse);
}
