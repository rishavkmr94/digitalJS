package com.learn.productservice.Dtos;

import com.learn.productservice.models.Category;
import com.learn.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PartialUpdateRequestDto {
    private String title;
    private String description;
    private double price;
    private Category category;

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        Category category = new Category();
        category.setId(this.getCategory().getId());
        category.setName(this.getCategory().getName());
        category.setDescription(this.getCategory().getDescription());
        product.setCategory(category);
        return product;
    }
}
