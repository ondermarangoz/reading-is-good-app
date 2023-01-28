package com.onder.readingisgood.service.statistic;

import com.onder.readingisgood.domain.repository.OrderRepository;
import com.onder.readingisgood.service.statistic.request.RequestGetMonthlyOrderStatistics;
import com.onder.readingisgood.service.statistic.response.ResponseGetMonthlyOrderStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {

    @Mock
    private OrderRepository mockOrderRepository;

    private StatisticServiceImpl statisticServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        statisticServiceImplUnderTest = new StatisticServiceImpl(mockOrderRepository);
    }

    @Test
    void testTotalOrderCount() {
        when(mockOrderRepository.countOrderMonth(1)).thenReturn(0);

        final Integer result = statisticServiceImplUnderTest.totalOrderCount(1);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void testTotalPurchaseAmount() {
        when(mockOrderRepository.totalPurchaseAmountMonth(1)).thenReturn(0.0f);

        final Float result = statisticServiceImplUnderTest.totalPurchaseAmount(1);
        assertThat(result).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    void testTotalAmountOfPurchasedBooks() {
        when(mockOrderRepository.countOrderedBookMonth(1)).thenReturn(0);

        final Integer result = statisticServiceImplUnderTest.totalAmountOfPurchasedBooks(1);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void testGetMonthlyOrderStatistics() {
        final RequestGetMonthlyOrderStatistics request = new RequestGetMonthlyOrderStatistics();
        when(mockOrderRepository.countOrderMonth(1)).thenReturn(0);
        when(mockOrderRepository.totalPurchaseAmountMonth(1)).thenReturn(0.0f);
        when(mockOrderRepository.countOrderedBookMonth(1)).thenReturn(0);

        final ResponseGetMonthlyOrderStatistics result = statisticServiceImplUnderTest.getMonthlyOrderStatistics(
                request);

    }
}
