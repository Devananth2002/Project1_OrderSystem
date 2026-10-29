package com.example.shoppingApplication.user.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    public Integer userId;
    public String userName;
    public String emailId;
    public Long phoneNo;
    public String address;
    public List<OrderResponse> orderResponse;

}
