package com.learn.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") int id) {
        return "this is a new product "+id;
    }

    @RequestMapping(name = "RISHAV", value = "/getProduct")
    public String getCustom(){
        return "this is a rishav custom product";
    }
}
