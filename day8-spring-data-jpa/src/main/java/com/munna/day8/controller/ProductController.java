package com.munna.day8.controller;


import com.munna.day8.entity.Product;
import com.munna.day8.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Read
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Read by ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Update
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existing = productRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            return productRepository.save(existing);
        }
        return null;
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully!";
    }
}

/*
Final Understanding

| Concept                | Description                                          |
| ---------------------- | ---------------------------------------------------- |
| **@Entity**            | Tells JPA this class should be stored in a DB table. |
| **@Table(name="...")** | Customizes the table name.                           |
| **@Id**                | Primary key of the table.                            |
| **@GeneratedValue**    | Auto-generates the ID value (auto increment).        |
| **JpaRepository**      | Gives you CRUD methods for free.                     |
| **H2**                 | Temporary in-memory DB — no setup needed.            |


*/