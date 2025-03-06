package com.learn.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @Column(name = "category_name")
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
