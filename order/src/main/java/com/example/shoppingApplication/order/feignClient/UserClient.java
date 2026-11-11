package com.example.shoppingApplication.order.feignClient;

import com.example.shoppingApplication.order.Response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "http://user-service:8081", path = "/api/user")
public interface UserClient {

    @GetMapping("/checkUserExists/{userId}")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable Integer userId);

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId);

}
