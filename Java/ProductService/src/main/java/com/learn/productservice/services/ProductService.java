package com.learn.productservice.services;

import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id) throws ProductNotFoundException;

    ResponseEntity<Product> updateProduct(int id, Product product);

    HttpStatus deleteProduct(Long id) throws ProductNotFoundException;
}
