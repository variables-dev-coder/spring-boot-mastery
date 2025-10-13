package com.munna.springboot.day10.controller;


import com.munna.springboot.day10.entity.Product;
import com.munna.springboot.day10.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        return service.save(p);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return service.delete(id);
    }
}

