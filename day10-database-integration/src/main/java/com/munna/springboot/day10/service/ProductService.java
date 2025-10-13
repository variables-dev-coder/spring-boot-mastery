package com.munna.springboot.day10.service;

import com.munna.springboot.day10.entity.Product;
import com.munna.springboot.day10.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product save(Product p) {
        return repo.save(p);
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public String delete(Long id) {
        repo.deleteById(id);
        return "Deleted product with ID " + id;
    }
}
