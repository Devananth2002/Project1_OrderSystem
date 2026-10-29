package com.example.shoppingApplication.order.Response;

import lombok.Data;

@Data
public class UserResponse {

    public Integer userId;
    public String userName;
    public String emailId;
    public Long phoneNo;
    public String address;
}
