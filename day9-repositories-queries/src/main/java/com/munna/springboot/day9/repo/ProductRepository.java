package com.munna.springboot.day9.repo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import com.munna.springboot.day9.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived queries (method name -> query)
    List<Product> findByName(String name);
    List<Product> findByNameContainingIgnoreCase(String fragment);
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findTop3ByOrderByPriceDesc();

    // Pagination example
    Page<Product> findByPriceBetween(Double low, Double high, Pageable pageable);

    // JPQL query (entity names and fields)
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findByNameJPQL(@Param("name") String name);

    // Native SQL query (table/column names)
    @Query(value = "SELECT * FROM products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))",
           nativeQuery = true)
    List<Product> findByNameNative(@Param("name") String name);

    // Update using JPQL - requires @Modifying + transaction
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = :price WHERE p.id = :id")
    int updatePriceById(@Param("id") Long id, @Param("price") Double price);
}



// Notes

// CrudRepository gives basic CRUD.

// JpaRepository extends PagingAndSortingRepository and adds JPA-specific methods 
//(findAll(Pageable), saveAll, flush, saveAndFlush, etc.). Use JpaRepository in most apps.

// Derived methods are converted by Spring Data to SQL automatically (based on naming conventions).






