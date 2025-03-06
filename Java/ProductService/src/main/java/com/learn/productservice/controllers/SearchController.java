package com.learn.productservice.controllers;

import com.learn.productservice.models.Product;
import com.learn.productservice.services.searchservices.ISearchService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    //sample request
    //GET /products/search?pageSize=3&pageCount=1&queryName=rishav&sorts=price:asc,name:desc

    private ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public Page<Product> searchProducts(@RequestParam String query,
                                        //@RequestParam(required = false) List<Pair<String,String>> filterAttributes,
                                        @RequestParam(defaultValue = "0") int pageSize,
                                        @RequestParam(defaultValue = "10") int pageCount,
                                        @RequestParam(required = false) List<String> sortList) {
        return searchService.searchProducts(query, pageSize, pageCount, sortList);
    }

}
