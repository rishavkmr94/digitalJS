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
        if(productRepository.findByName(product.getTitle()).isPresent()){
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
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()){
            return new ArrayList<>();
        }
        else {
            return products;
        }
    }

    @Override
    public List<Product> getAllProductsByCategoryName(String categoryName) {
        Optional<List<Product>> products = productRepository.findAllByCategory_Name(categoryName);
        return products.orElse(new ArrayList<>());
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> product1 = productRepository.findById(id);
        if(product1.isEmpty())throw new ProductNotFoundException("Product not found");
        if (categoryRepository.findById(product.getCategory().getId()).isEmpty()){
            Category category = new Category();
            category.setName(product.getCategory().getName());
            category.setDescription(product.getCategory().getDescription());
            categoryRepository.save(category);
        }
        product.setId(id);
        return productRepository.save(product);
    }


    @Override
    public HttpStatus deleteProduct(Long id) throws ProductNotFoundException{
        if (productRepository.findById(id).isEmpty()) throw new ProductNotFoundException("Product not found");
        productRepository.customDeleteById(id);
        return HttpStatus.OK;
    }
}
