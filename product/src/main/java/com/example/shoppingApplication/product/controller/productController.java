package com.example.shoppingApplication.product.controller;

import com.example.shoppingApplication.product.model.Product;
import com.example.shoppingApplication.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class productController{


    public final ProductService productService;

    public productController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody String productJson){
        HttpStatus status = productService.addProduct(productJson) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(status);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody String productJson){
        HttpStatus status = productService.updateProduct(productJson) ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE;
        return new ResponseEntity<>(status);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Integer productId){
        return productService.getProductById(productId);
    }

    @GetMapping
    public List<Product> getProduct(){
        return productService.getProduct();
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable  Integer productId){
        boolean deleted = productService.deleteProduct(productId);
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(deleted, status);
    }

    @GetMapping("/checkProductExists/{productId}")
    public ResponseEntity<?> checkProductExists(@PathVariable Integer productId){
        boolean exists = productService.checkProductExists(productId);
        HttpStatus status = exists ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(exists, status);
    }

}