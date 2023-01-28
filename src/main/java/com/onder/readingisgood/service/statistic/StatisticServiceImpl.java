package com.onder.readingisgood.service.statistic;

import com.onder.readingisgood.domain.repository.OrderRepository;
import com.onder.readingisgood.service.statistic.model.MonthlyOrderStatistics;
import com.onder.readingisgood.service.statistic.request.RequestGetMonthlyOrderStatistics;
import com.onder.readingisgood.service.statistic.response.ResponseGetMonthlyOrderStatistics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final OrderRepository orderRepository;
    private final StatisticServiceMapper mapper = Mappers.getMapper(StatisticServiceMapper.class);
    @Override
    public Integer totalOrderCount(int month) {
        return orderRepository.countOrderMonth(month);

    }

    @Override
    public Float totalPurchaseAmount(int month) {
        return orderRepository.totalPurchaseAmountMonth(month);
    }

    @Override
    public Integer totalAmountOfPurchasedBooks(int month) {
        return orderRepository.countOrderedBookMonth(month);
    }


    @Override
    public ResponseGetMonthlyOrderStatistics getMonthlyOrderStatistics(RequestGetMonthlyOrderStatistics request) {
        ResponseGetMonthlyOrderStatistics responseGetMonthlyOrderStatistics = new ResponseGetMonthlyOrderStatistics();
        List<MonthlyOrderStatistics> monthlyOrderStatisticsList = new ArrayList<>();
        for (Month month: Month.values()) {
            MonthlyOrderStatistics monthlyOrderStatistics = new MonthlyOrderStatistics();
            monthlyOrderStatistics.setMonth(month.getDisplayName(TextStyle.FULL, LocaleContextHolder.getLocale()));
            monthlyOrderStatistics.setTotalOrderCount(totalOrderCount(month.getValue()));
            monthlyOrderStatistics.setTotalPurchasedAmount(totalPurchaseAmount(month.getValue()));
            monthlyOrderStatistics.setTotalBookCount(totalAmountOfPurchasedBooks(month.getValue()));
            monthlyOrderStatisticsList.add(monthlyOrderStatistics);
        }
        responseGetMonthlyOrderStatistics.setMonthlyOrderStatisticsList(monthlyOrderStatisticsList);

        return responseGetMonthlyOrderStatistics;
    }
}
