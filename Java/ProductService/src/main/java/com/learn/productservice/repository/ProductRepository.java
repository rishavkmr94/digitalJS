package com.learn.productservice.repository;

import com.learn.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);

    Optional<Product> findByTitle(String title);

    @Override
    void delete(Product product);
}
