package com.example.shoppingApplication.notifications.Response;

import lombok.Data;

@Data
public class OrderResponse {

    public Integer orderId;
    public String orderName;
    public String orderAddress;
    public Integer productId;
    public Integer customerId;
    public String customerName;
    public String customerEmail;
}
