package com.learn.productservice.services;

import com.learn.productservice.exceptions.ProductAlreadyExistsException;
import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product) throws ProductAlreadyExistsException;

    List<Product> getAllProducts();

    Product getProductById(Long id) throws ProductNotFoundException;

    ResponseEntity<Product> updateProduct(Long id, Product product);

    HttpStatus deleteProduct(Long id) throws ProductNotFoundException;
}
