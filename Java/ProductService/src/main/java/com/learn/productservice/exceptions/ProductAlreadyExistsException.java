package com.learn.productservice.exceptions;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
