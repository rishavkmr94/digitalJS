package com.learn.productservice.repository;

public interface CustomQueries {
    String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id=?";
}
