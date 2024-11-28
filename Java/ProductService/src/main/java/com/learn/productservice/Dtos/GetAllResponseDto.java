package com.learn.productservice.Dtos;

import com.learn.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetAllResponseDto {
    List<GetProductDto> products;
}
