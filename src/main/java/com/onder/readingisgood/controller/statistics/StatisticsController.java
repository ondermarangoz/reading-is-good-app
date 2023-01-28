package com.onder.readingisgood.controller.statistics;

import com.onder.readingisgood.domain.mapper.StatisticMapper;
import com.onder.readingisgood.domain.model.request.GetMonthlyOrderStatisticsRequestModel;
import com.onder.readingisgood.domain.model.response.GetMonthlyOrderStatisticsResponseModel;
import com.onder.readingisgood.service.statistic.StatisticService;
import com.onder.readingisgood.service.statistic.response.ResponseGetMonthlyOrderStatistics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticService statisticService;
    private final StatisticMapper mapper = Mappers.getMapper(StatisticMapper.class);
    @PostMapping("/getMonthlyStatistics")
    public GetMonthlyOrderStatisticsResponseModel getMonthlyOrderStatistics(GetMonthlyOrderStatisticsRequestModel requestModel){

        ResponseGetMonthlyOrderStatistics serviceResponse = statisticService.
                getMonthlyOrderStatistics(mapper.mapStatisticsRequestModelToStatisticsRequest(requestModel));

        return mapper.mapStatisticsResponseToStatisticsResponseModel(serviceResponse);


    }
}
