package com.learn.productservice.Dtos;

import com.learn.productservice.models.Category;
import com.learn.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private double price;
    private Category category;

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.getPrice());
        product.setCategory(this.getCategory());
        return product;
    }
}
