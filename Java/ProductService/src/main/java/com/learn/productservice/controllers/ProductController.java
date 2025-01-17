package com.learn.productservice.controllers;

import com.learn.productservice.Dtos.CreateProductRequestDto;
import com.learn.productservice.Dtos.GetAllResponseDto;
import com.learn.productservice.Dtos.GetProductDto;
import com.learn.productservice.Dtos.PartialUpdateDto;
import com.learn.productservice.Enums.ResponseStatus;
import com.learn.productservice.exceptions.ProductNotFoundException;
import com.learn.productservice.models.Product;
import com.learn.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public GetProductDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return GetProductDto.fromProduct(product);
    }

    @GetMapping
    public GetAllResponseDto getAllProducts() {
        List<Product> products = productService.getAllProducts();
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

    @DeleteMapping
    public ResponseStatus deleteProduct(@PathVariable Long id) {
        return ResponseStatus.SUCCESS;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody PartialUpdateDto partialUpdateDto) {
        productService.updateProduct(id,partialUpdateDto.toProduct());
        return ResponseEntity.ok(partialUpdateDto.toProduct());
    }
}
