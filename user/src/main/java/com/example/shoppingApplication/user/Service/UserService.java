package com.example.shoppingApplication.user.Service;

import com.example.shoppingApplication.user.FeignClient.OrderClient;
import com.example.shoppingApplication.user.Model.User;
import com.example.shoppingApplication.user.Repository.UserRepository;
import com.example.shoppingApplication.user.Response.OrderResponse;
import com.example.shoppingApplication.user.Response.UserResponse;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    public final UserRepository userRepository;
    public final ObjectMapper objectMapper;
    public final OrderClient orderClient;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper, OrderClient orderClient) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.orderClient = orderClient;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer userId){
        return userRepository.findById(userId);
    }

    public boolean addUser(String userDetails){
        try{
            User user = objectMapper.readValue(userDetails, User.class);
            //System.out.println("Debug" + user.getUserName());
            if(user.getUserName() == null) return false;
            userRepository.save(user);
        } catch (Exception e){
            System.out.println("Error while adding user " + e);
            return false;
        }

        return true;
    }

    public boolean checkUserExists(Integer userId){
        return userRepository.existsById(userId);
    }

    public boolean deleteUser(Integer userId){
        if(!checkUserExists(userId)) return false;
        userRepository.deleteById(userId);
        return true;
    }

    public boolean updateUser(String userDetails){
        User user = objectMapper.readValue(userDetails, User.class);
        if(user.getUserId() == null || !checkUserExists(user.getUserId())) return false;
        userRepository.save(user);
        return true;
    }

    public List<OrderResponse> getOrders(Integer customerId){
        if(customerId == null) return null;
        List<OrderResponse> orderDetails = orderClient.getOrderByCustomerId(customerId).getBody();
        if(orderDetails == null) return null;
        return orderDetails.stream()
                .map(order -> {
                    OrderResponse response = new OrderResponse();
                    response.setOrderId(order.getOrderId());
                    response.setOrderName(order.getOrderName());
                    response.setOrderAddress(order.getOrderAddress());
                    response.setProductId(order.getProductId());
                    response.setCustomerId(order.getCustomerId());
                    return response;
                })
                .toList();
    }

}
