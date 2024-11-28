package com.learn.productservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
    private String category;
}
