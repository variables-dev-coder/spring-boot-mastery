package com.munna.springboot.day9.repo;

import org.springframework.data.repository.CrudRepository;

import com.munna.springboot.day9.model.Product;

public interface ProductCrudRepository extends CrudRepository<Product, Long> {
	// inherits save, findById, findAll, delete, etc.

}
