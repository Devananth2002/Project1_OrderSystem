package com.example.shoppingApplication.order.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "http://product-service:8080", path = "/api/product")
public interface ProductClient {

    @GetMapping("/checkProductExists/{productId}")
    public ResponseEntity<Boolean> checkProductExists(@PathVariable Integer productId);

}
