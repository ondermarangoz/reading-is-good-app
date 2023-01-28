package com.onder.readingisgood.service.order;

import com.onder.readingisgood.domain.entity.Book;
import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.repository.BookRepository;
import com.onder.readingisgood.domain.repository.OrderRepository;
import com.onder.readingisgood.infrastucture.exception.BadRequestException;
import com.onder.readingisgood.infrastucture.exception.BookNotFoundException;
import com.onder.readingisgood.infrastucture.exception.OrderNotFoundException;
import com.onder.readingisgood.service.order.model.GetOrderDTO;
import com.onder.readingisgood.service.order.model.OrderDTO;
import com.onder.readingisgood.service.order.request.RequestGetOrders;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderServiceMapper mapper = Mappers.getMapper(OrderServiceMapper.class);

    @Transactional
    @Override
    public void orderBooks(List<OrderDTO> orderDTOList) {
        if (orderDTOList == null) {
            throw new BadRequestException("Order List is empty");
        }

        orderDTOList.forEach(orderDTO -> {
            if (orderDTO.getQuantity() < 1) {
                throw new BadRequestException("Quantity is equal or less then 0");
            }
            Book book = bookRepository.findById(orderDTO.getBookId())
                    .orElseThrow(() -> new BookNotFoundException("Book with id: " + orderDTO.getBookId() + " is not found."));
            //Selling book decreases the amount of book in the store and increases the amount of book sold.
            int totalCount = book.getTotalCount() - orderDTO.getQuantity();
            if (totalCount < 0) {
                throw new BadRequestException("Not enough book in store to sell.");
            }
            int sold = book.getSold() + orderDTO.getQuantity();
            log.info("Total amount is decreased and sold amount increased.");
            book.setTotalCount(totalCount);
            book.setSold(sold);
            Order order = new Order();
            order.setBook(book);
            order.setOrderDate(new Date());
            order.setOrderCount(orderDTO.getQuantity());
            order.setPrice(book.getPrice());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            order.setUser((User) auth.getPrincipal());
            orderRepository.save(order);
            bookRepository.save(book);
        });
    }

    @Override
    public ResponseGetOrders getOrders(RequestGetOrders requestGetOrders) {
        ResponseGetOrders responseGetOrders = new ResponseGetOrders();
        Date startDate;
        Date endDate;
        if (Objects.nonNull(requestGetOrders) && requestGetOrders.getEndDate() != null && requestGetOrders.getStartDate() != null) {
            startDate = requestGetOrders.getStartDate();
            endDate = requestGetOrders.getEndDate();
        } else {
            throw new BadRequestException("start or end date cannot be null");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        List<Order> orders = orderRepository.findAllByUserAndOrderDateBetween(user, startDate, endDate);

        List<GetOrderDTO> getOrderList = mapper.mapOrderListToOrderDtoList(orders);
        responseGetOrders.setGetOrderList(getOrderList);

        return responseGetOrders;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id:" + id + " is not found."));
        return mapper.mapOrderToOrderDto(order);
    }
}
