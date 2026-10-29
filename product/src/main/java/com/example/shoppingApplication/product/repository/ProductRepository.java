package com.example.shoppingApplication.product.repository;

import com.example.shoppingApplication.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    Product findByProductId(Integer productId);
}
