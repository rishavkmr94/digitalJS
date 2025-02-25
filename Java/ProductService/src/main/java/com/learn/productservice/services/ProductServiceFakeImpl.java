package com.learn.productservice.services;

import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
@Profile("FakeStore")
public class ProductServiceFakeImpl implements ProductService {
    private final RestTemplate restTemplate;

    private final RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public ProductServiceFakeImpl(RestTemplate restTemplate, RedisTemplate<String,Object>  redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
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
        Product product = (Product) redisTemplate.opsForHash().get("products", id);
        if (product == null) {
            product = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, Product.class);
            if (product == null) {
                throw new ProductNotFoundException("product not found");
            }
            redisTemplate.opsForHash().put("products", id, product);
            System.out.println("from fakestore");
        }
        else {
            System.out.println("from redis");
        }
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        String url = "https://fakestoreapi.com/products/" + id;
        HttpEntity<Product> request = new HttpEntity<>(product);
        return restTemplate.exchange(url, HttpMethod.PUT, request, Product.class).getBody();
    }

    @Override
    public HttpStatus deleteProduct(Long id) throws ProductNotFoundException {
        Product product = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, Product.class);
        if (product == null) {
            throw new ProductNotFoundException("product not found");
        }
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return HttpStatus.OK;
    }
}
