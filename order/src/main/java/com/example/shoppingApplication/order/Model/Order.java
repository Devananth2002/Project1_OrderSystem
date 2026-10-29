package com.example.shoppingApplication.order.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    public Integer orderId;

    @Column(name = "orderName")
    public String orderName;

    @Column(name = "orderAddress")
    public String orderAddress;

    @Column(name = "productId")
    public Integer productId;

    @Column(name = "customerId")
    public Integer customerId;

}
