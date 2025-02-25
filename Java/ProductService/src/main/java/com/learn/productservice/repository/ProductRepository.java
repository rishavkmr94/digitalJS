package com.learn.productservice.repository;

import com.learn.productservice.models.Product;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);

    Optional<Product> findByTitle(String title);
    Optional<Product> findById(Long id);

    List<Product> findAll();

    // find all product by category name using JPA abstracted method
    Optional<List<Product>> findAllByCategory_Name(String name);

    //get product by id using JPA query
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findById(@Param("id") long id);

    //using native sql query, custom query interface for static queries
    @Modifying
    @Transactional
    @Query(
            value = CustomQueries.DELETE_PRODUCT_BY_ID,
            nativeQuery = true)
    int customDeleteById(Long id);
}
