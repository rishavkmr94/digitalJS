package com.learn.productservice.services;

import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Profile("FakeStore")
public class ProductServiceFakeImpl implements ProductService {
    private final RestTemplate restTemplate;

    public ProductServiceFakeImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        return restTemplate.postForObject("https://fakestoreapi.com/products", product, Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        Product[] productArray = restTemplate.getForObject("https://fakestoreapi.com/products", Product[].class);
        return Arrays.asList(productArray);
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Product product = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, Product.class);
        if (product == null) {
            throw new ProductNotFoundException("product not found");
        }
        return product;
    }

    @Override
    public ResponseEntity<Product> updateProduct(int id, Product product) {
        product.setId(id);
        String url = "https://fakestoreapi.com/products/" + id;
        HttpEntity<Product> request = new HttpEntity<>(product);
        return restTemplate.exchange(url, HttpMethod.PUT, request, Product.class);
    }
}
