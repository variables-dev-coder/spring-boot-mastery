package com.munna.springboot.day6.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day6.exception.ProductNotFoundException;
import com.munna.springboot.day6.model.Product;




@RestController
@RequestMapping("/products")
public class ProductController {
	
	 private static final Map<Integer, Product> productRepo = new HashMap<>();

	    static {
	        productRepo.put(1, new Product(1, "Laptop", 55000));
	        productRepo.put(2, new Product(2, "Phone", 25000));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable int id) {
	        Product product = productRepo.get(id);
	        if (product == null) {
	            throw new ProductNotFoundException("Product with id " + id + " not found!");
	        }
	        return ResponseEntity.ok(product);
	    }

	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        productRepo.put(product.getId(), product);
	        return ResponseEntity.status(HttpStatus.CREATED).body(product);
	    }

}
