package com.learn.productservice.Dtos;

import com.learn.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PartialUpdateDto {
    private String title;
    private String description;
    private double price;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setCategory(this.getCategory());
        return product;
    }
}
