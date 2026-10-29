package com.example.shoppingApplication.user.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer userId;

    @Column(name = "userName")
    public String userName;

    @Column(name = "emailId")
    public String emailId;

    @Column(name = "phoneNo")
    public Long phoneNo;

    @Column(name = "address")
    public String address;
}
