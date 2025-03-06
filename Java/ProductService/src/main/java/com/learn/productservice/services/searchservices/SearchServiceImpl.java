package com.learn.productservice.services.searchservices;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.learn.productservice.models.Product;
import com.learn.productservice.repository.ProductRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService{

    private final ProductRepository productRepository;
    @Autowired
    public SearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageCount, List<String> sortList) {
        if (sortList.isEmpty()){
            return productRepository.findAllByTitleLike(query, PageRequest.of(pageCount,pageSize));
        }
        String[] sortFirst = sortList.getFirst().split(":");
        Sort sort = sortFirst[0].equalsIgnoreCase("asc") ? Sort.by(sortFirst[0]): Sort.by(sortFirst[0]).descending();

        for(int i=1;i<sortList.size();i++){
            String[] sortPair = sortList.get(i).split(":");
            if(sortPair[1].equalsIgnoreCase("asc")){
                sort=sort.and(Sort.by(sortPair[0]));
            }
            else{
                sort=sort.and(Sort.by(sortPair[0]).descending());
            }
        }
        return productRepository.findAllByTitleLike(query, PageRequest.of(pageCount,pageSize, sort));
    }
}
