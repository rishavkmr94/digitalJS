package com.learn.productservice.services.searchservices;

import com.learn.productservice.models.Product;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {

    public Page<Product> searchProducts(String query, Integer page, Integer size, List<String> sortList);
}
