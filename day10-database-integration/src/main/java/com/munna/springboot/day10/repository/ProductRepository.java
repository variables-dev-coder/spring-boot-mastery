package com.munna.springboot.day10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.munna.springboot.day10.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
