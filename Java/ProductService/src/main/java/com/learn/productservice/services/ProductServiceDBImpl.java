package com.learn.productservice.services;

import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("DB")
public class ProductServiceDBImpl implements ProductService{
    @Override
    public Product createProduct(Product product) {
        System.out.println("this is DB implementation");
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<Product> updateProduct(int id, Product product) {
        return null;
    }
}
