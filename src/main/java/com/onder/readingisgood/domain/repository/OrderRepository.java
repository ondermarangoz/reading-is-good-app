package com.onder.readingisgood.domain.repository;

import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //@Query(value = "SELECT * from Orders where Orders.user= ?1",nativeQuery = true)
    List<Order> getOrdersByUser(Optional<User> user);

    List<Order> findAllByUserAndOrderDateBetween(User user, Date startDate, Date endDate);

    @Query(value = "select count(order_date) from Orders o where EXTRACT(month FROM o.order_date) = :month", nativeQuery = true)
    Integer countOrderMonth(int month);

    @Query(value = "select sum(order_count) from Orders o where EXTRACT(month FROM o.order_date) = :month", nativeQuery = true)
    Integer countOrderedBookMonth(int month);

    @Query(value = "select sum(price) from Orders o where EXTRACT(month FROM o.order_date) = :month", nativeQuery = true)
    Float totalPurchaseAmountMonth(int month);

}
