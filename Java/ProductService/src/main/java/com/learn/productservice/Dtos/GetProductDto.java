package com.learn.productservice.Dtos;

import com.learn.productservice.models.Category;
import com.learn.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private Category category;

    public static GetProductDto fromProduct(Product product) {
        GetProductDto responseDto = new GetProductDto();
        responseDto.id = product.getId();
        responseDto.title = product.getTitle();
        responseDto.description = product.getDescription();
        responseDto.price = product.getPrice();
        Category category = new Category();
        category.setId(product.getCategory().getId());
        category.setName(product.getCategory().getName());
        category.setDescription(product.getCategory().getDescription());
        responseDto.category = category;
        return responseDto;
    }
}
