package com.learn.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    private double price;
    @ManyToOne
    private Category category;
}
