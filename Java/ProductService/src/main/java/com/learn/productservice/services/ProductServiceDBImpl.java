package com.learn.productservice.services;

import com.learn.productservice.exceptions.ProductAlreadyExistsException;
import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Category;
import com.learn.productservice.models.Product;
import com.learn.productservice.repository.CategoryRepository;
import com.learn.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("DB")
public class ProductServiceDBImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(Product product) throws ProductAlreadyExistsException{
        if(productRepository.findByTitle(product.getTitle()).isPresent()){
            throw new ProductAlreadyExistsException("Product already exists, please update product");
        }
        Optional<Category> category=categoryRepository.findByName(product.getCategory().getName());
        if (category.isEmpty()) {
            Category category1 = new Category();
            category1.setName(product.getCategory().getName());
            category1.setDescription(product.getCategory().getDescription());
            category1=categoryRepository.save(category1);
            product.setCategory(category1);
        }
        else {
            product.setCategory(category.get());
        }
        return productRepository.save(product);
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
    public ResponseEntity<Product> updateProduct(Long id, Product product) {
        return null;
    }


    @Override
    public HttpStatus deleteProduct(Long id) {
        return null;
    }
}
