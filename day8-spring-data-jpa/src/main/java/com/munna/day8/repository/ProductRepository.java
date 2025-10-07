package com.munna.day8.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.munna.day8.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}


/*

This gives all CRUD operations automatically:

save()

findAll()

findById()

deleteById()

don’t have to write SQL yourself!


 */
