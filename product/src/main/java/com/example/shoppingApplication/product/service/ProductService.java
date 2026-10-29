package com.example.shoppingApplication.product.service;

import com.example.shoppingApplication.product.model.Product;
import com.example.shoppingApplication.product.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class ProductService {

    public final ObjectMapper objectMapper;
    public final ProductRepository productRepository;

    //Dependency injection via constructor
    public ProductService(ObjectMapper objectMapper, ProductRepository productRepository){
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }

    public boolean addProduct(String productJson){
        try {
            Product product = objectMapper.readValue(productJson, Product.class);

            if(product.getProductId() == null){
                return false;
            } else {
                Product existingproduct = productRepository.findByProductId(product.getProductId());
                if(existingproduct!=null){
                    System.out.println("Product with same id already present");
                    return false;
                }
            }
            productRepository.save(product);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteProduct(Integer productId){
        try {
            Product product = productRepository.findByProductId(productId);
            if(product!=null) {
                productRepository.delete(product);
            } else {
                System.out.println("Product id not found");
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean updateProduct(String productJson){
        try {
            Product product = objectMapper.readValue(productJson, Product.class);

            if(product.getProductId() == null){
                return false;
            }
            Product existingProduct = productRepository.findByProductId(product.getProductId());

            if (existingProduct != null) {
                if(product.getProductName() != null){
                    existingProduct.setProductName(product.getProductName());
                }

                if(product.getProductPrice() != null && product.getProductPrice() > 0) {
                    existingProduct.setProductPrice(product.getProductPrice());
                }
                productRepository.save(existingProduct);
            } else {
                System.out.println("No existing product found to update");
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    public Product getProductById(Integer productId){
        return productRepository.findByProductId(productId);
    }

    public Boolean checkProductExists(Integer productId) {
        return productRepository.existsById(productId);
    }
}
