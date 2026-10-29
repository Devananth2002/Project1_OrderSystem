package com.example.shoppingApplication.user.Response;

import lombok.Data;

@Data
public class OrderResponse {

    public Integer orderId;
    public String orderName;
    public String orderAddress;
    public String productId;
    public String customerId;

}
