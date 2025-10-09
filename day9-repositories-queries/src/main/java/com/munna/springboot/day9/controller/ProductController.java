package com.munna.springboot.day9.controller;

import org.springframework.web.bind.annotation.*;

import com.munna.springboot.day9.model.Product;
import com.munna.springboot.day9.repo.ProductRepository;

import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        p.setCreatedAt(LocalDateTime.now());
        return repo.save(p);
    }

    @GetMapping
    public List<Product> all() {
        return repo.findAll();
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String q) {
        return repo.findByNameContainingIgnoreCase(q);
    }

    @GetMapping("/top3")
    public List<Product> top3() {
        return repo.findTop3ByOrderByPriceDesc();
    }

    @GetMapping("/jpql")
    public List<Product> byJpql(@RequestParam String name) {
        return repo.findByNameJPQL(name);
    }

    @GetMapping("/native")
    public List<Product> byNative(@RequestParam String name) {
        return repo.findByNameNative(name);
    }

    @PutMapping("/{id}/price")
    public String updatePrice(@PathVariable Long id, @RequestParam Double price) {
        int updated = repo.updatePriceById(id, price);
        return updated > 0 ? "updated" : "not found";
    }

    // pagination example
    @GetMapping("/between")
    public Page<Product> between(@RequestParam Double low,
                                 @RequestParam Double high,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return repo.findByPriceBetween(low, high, PageRequest.of(page, size, Sort.by("price")));
    }
}
