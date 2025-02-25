package com.learn.productservice.controllers;

import com.learn.productservice.Dtos.*;
import com.learn.productservice.exceptions.ProductAlreadyExistsException;
import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import com.learn.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

//task - create a repo method to get all products of category using _ feature of JPA
//task - write a jpa query not function
//task - write a native query ( only used in case of very complex operations or jpa not optimal)

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public GetProductDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) throws ProductAlreadyExistsException {
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return GetProductDto.fromProduct(product);
    }

    @GetMapping
    //http://localhost:8080/products?categoryName=category2
    public GetAllResponseDto getAllProducts(@RequestParam(required = false) String categoryName) {
        List<Product> products =null;
        if(categoryName!=null){
            products = productService.getAllProductsByCategoryName(categoryName);
        }
        else{
            products = productService.getAllProducts();
        }

        GetAllResponseDto getAllResponseDto = new GetAllResponseDto();
        List<GetProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(GetProductDto.fromProduct(product));
        }
        getAllResponseDto.setProducts(productDtos);
        return getAllResponseDto;
    }

    @GetMapping("/{id}")
    public GetProductDto GetSingleProduct(@PathVariable Long id) throws ProductNotFoundException {
        return GetProductDto.fromProduct(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        return productService.deleteProduct(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GetProductDto> updateProduct(@PathVariable Long id, @RequestBody PartialUpdateRequestDto partialUpdateDto) throws ProductNotFoundException {
        Product product = productService.updateProduct(id,partialUpdateDto.toProduct());
        return ok(GetProductDto.fromProduct(product));
    }
}
