package com.learn.productservice.services;

import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id) throws ProductNotFoundException;

    ResponseEntity<Product> updateProduct(int id, Product product);
}
