package com.example.shoppingApplication.order.Repository;

import com.example.shoppingApplication.order.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findAllByOrderId(Integer orderId);
    List<Order> getOrderByCustomerId(Integer customerId);
}
