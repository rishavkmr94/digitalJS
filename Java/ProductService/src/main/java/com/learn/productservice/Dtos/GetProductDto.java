package com.learn.productservice.Dtos;

import com.learn.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetProductDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String category;

    public static GetProductDto fromProduct(Product product) {
        GetProductDto responseDto = new GetProductDto();
        responseDto.id = product.getId();
        responseDto.title = product.getTitle();
        responseDto.description = product.getDescription();
        responseDto.price = product.getPrice();
        responseDto.category = product.getCategory();
        return responseDto;
    }
}
