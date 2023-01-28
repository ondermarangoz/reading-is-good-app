package com.onder.readingisgood.service.order;

import com.onder.readingisgood.domain.entity.Book;
import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.repository.BookRepository;
import com.onder.readingisgood.domain.repository.OrderRepository;
import com.onder.readingisgood.infrastucture.exception.OrderNotFoundException;
import com.onder.readingisgood.service.order.model.OrderDTO;
import com.onder.readingisgood.service.order.request.RequestGetOrders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private BookRepository mockBookRepository;
    @Mock
    private OrderRepository mockOrderRepository;

    private OrderServiceImpl orderServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        orderServiceImplUnderTest = new OrderServiceImpl(mockBookRepository, mockOrderRepository);
    }

    @Test
    void testOrderBooks() {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBookId(0L);
        orderDTO.setQuantity(0);
        final List<OrderDTO> orderDTOList = List.of(orderDTO);

        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder()
                .price(0.0f)
                .totalCount(0)
                .sold(0)
                .build());
        order.setUser(User.builder().build());
        final Optional<Book> book = Optional.of(
                new Book(0L, "title", "author", "category", 0.0f, 0, 0, List.of(order)));
        when(mockBookRepository.findById(0L)).thenReturn(book);

        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder()
                .price(0.0f)
                .totalCount(0)
                .sold(0)
                .build());
        order1.setUser(User.builder().build());
        when(mockOrderRepository.save(any(Order.class))).thenReturn(order1);

        final Order order2 = new Order();
        order2.setOrderId(0L);
        order2.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order2.setOrderCount(0);
        order2.setPrice(0.0f);
        order2.setBook(Book.builder()
                .price(0.0f)
                .totalCount(0)
                .sold(0)
                .build());
        order2.setUser(User.builder().build());
        final Book book1 = new Book(0L, "title", "author", "category", 0.0f, 0, 0, List.of(order2));
        when(mockBookRepository.save(any(Book.class))).thenReturn(book1);

        orderServiceImplUnderTest.orderBooks(orderDTOList);

        verify(mockOrderRepository).save(any(Order.class));
        verify(mockBookRepository).save(any(Book.class));
    }


    @Test
    void testGetOrders() {

        final RequestGetOrders requestGetOrders = new RequestGetOrders();
        requestGetOrders.setStartDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        requestGetOrders.setEndDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder()
                .price(0.0f)
                .totalCount(0)
                .sold(0)
                .build());
        order.setUser(User.builder().build());
        final List<Order> orders = List.of(order);
        when(mockOrderRepository.findAllByUserAndOrderDateBetween(null, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(orders);

        final ResponseGetOrders result = orderServiceImplUnderTest.getOrders(requestGetOrders);

    }

    @Test
    void testGetOrdersRepositoryReturnsNoItems() {
        final RequestGetOrders requestGetOrders = new RequestGetOrders();
        requestGetOrders.setStartDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        requestGetOrders.setEndDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(mockOrderRepository.findAllByUserAndOrderDateBetween(null, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());
        final ResponseGetOrders result = orderServiceImplUnderTest.getOrders(requestGetOrders);

    }

    @Test
    void testGetOrderById() {
        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder()
                .price(0.0f)
                .totalCount(0)
                .sold(0)
                .build());
        order1.setUser(User.builder().build());
        final Optional<Order> order = Optional.of(order1);
        when(mockOrderRepository.findById(0L)).thenReturn(order);

        final OrderDTO result = orderServiceImplUnderTest.getOrderById(0L);


    }

    @Test
    void testGetOrderByIdOrderRepositoryReturnsAbsent() {
        when(mockOrderRepository.findById(0L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> orderServiceImplUnderTest.getOrderById(0L)).isInstanceOf(OrderNotFoundException.class);
    }
}
