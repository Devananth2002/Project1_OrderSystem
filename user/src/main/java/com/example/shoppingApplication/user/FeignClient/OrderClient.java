package com.example.shoppingApplication.user.FeignClient;

import com.example.shoppingApplication.user.Response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order", url = "http://localhost:8082", path = "/api/order")
public interface OrderClient {

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrderByCustomerId(@PathVariable Integer customerId);

}
