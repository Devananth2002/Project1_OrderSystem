package com.example.shoppingApplication.product.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer productId;
    public String productName;
    public Integer productPrice;

}
