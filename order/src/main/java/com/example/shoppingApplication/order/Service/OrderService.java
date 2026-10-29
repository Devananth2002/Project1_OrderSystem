package com.example.shoppingApplication.order.Service;

import com.example.shoppingApplication.order.Constants.AppContant;
import com.example.shoppingApplication.order.Model.Order;
import com.example.shoppingApplication.order.Repository.OrderRepository;
import com.example.shoppingApplication.order.Response.OrderResponse;
import com.example.shoppingApplication.order.Response.UserResponse;
import com.example.shoppingApplication.order.feignClient.ProductClient;
import com.example.shoppingApplication.order.feignClient.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public final OrderRepository orderRepository;
    public final ObjectMapper objectMapper;
    public final UserClient userClient;
    public final ProductClient productClient;
    public final KafkaTemplate<Object, String> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, ObjectMapper objectMapper, UserClient userClient, ProductClient productClient, KafkaTemplate<Object, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
        this.userClient = userClient;
        this.productClient = productClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderByOrderId(Integer orderId){
        return orderRepository.findAllByOrderId(orderId);
    }

    public List<Order> getOrderByCustomerId(Integer orderId){
        return orderRepository.getOrderByCustomerId(orderId);
    }

    public boolean placeOrder(String orderDetails){
        Order order = objectMapper.readValue(orderDetails, Order.class);

        if(order.getCustomerId() == null) return false;

        ResponseEntity<Boolean> user = userClient.checkUserExists(order.getCustomerId());
        ResponseEntity<Boolean> product = productClient.checkProductExists(order.getProductId());

        if(Boolean.FALSE.equals(user.getBody()) || Boolean.FALSE.equals(product.getBody())) return false;

        orderRepository.save(order);

        OrderResponse orderResponse = objectMapper.readValue(orderDetails, OrderResponse.class);

        UserResponse userDetails = userClient.getUserById(order.getCustomerId()).getBody();

        orderResponse.setOrderId(order.getOrderId());

        if(userDetails.getUserName() != null)  orderResponse.setCustomerName(userDetails.getUserName());
        if(userDetails.getEmailId()!= null ) orderResponse.setCustomerEmail(userDetails.getEmailId());

        // send notification message to kafka
        String orderJsonMessage = objectMapper.writeValueAsString(orderResponse);
        kafkaTemplate.send(AppContant.ORDER_NOTIFICATION, orderJsonMessage);

        return true;
    }

}
