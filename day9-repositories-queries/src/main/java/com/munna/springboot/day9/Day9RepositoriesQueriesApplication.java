package com.munna.springboot.day9;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.munna.springboot.day9.model.Product;
import com.munna.springboot.day9.repo.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Day9RepositoriesQueriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day9RepositoriesQueriesApplication.class, args);
    }

    //This method runs automatically at startup
    @Bean
    CommandLineRunner dataLoader(ProductRepository repo) {
        return args -> {
            repo.saveAll(List.of(
                new Product(null, "Keyboard", 29.99, LocalDateTime.now()),
                new Product(null, "Mouse", 19.99, LocalDateTime.now()),
                new Product(null, "Monitor", 199.99, LocalDateTime.now()),
                new Product(null, "Laptop", 999.99, LocalDateTime.now()),
                new Product(null, "Laptop Sleeve", 25.0, LocalDateTime.now())
            ));
            System.out.println("Sample products added to H2 Database!");
        };
    }
}



/*
 Common derived keywords (use in method names)
 ---------------------------------------------
	findBy, findTop3By, findFirstBy
	And, Or
	Between, LessThan, GreaterThan
	Like, StartingWith, EndingWith, Containing
	IgnoreCase
	OrderBy<Field>Asc/Desc
	In, Not
	Example: List<Product> findTop5ByNameContainingIgnoreCaseOrderByPriceDesc(String q);
 
 
JPQL vs Native queries — when to use what
-----------------------------------------
JPQL: Use when you want database-agnostic queries and you want to query entities
		(use entity names/fields). Good default.

Native SQL: Use if you need DB-specific features or complex SQL not expressible in 
			JPQL (window functions, vendor-specific syntax).

Use @Query for custom queries and @Modifying + transaction for update/delete queries.


Testing repositories quickly
----------------------------

For small tests, use @SpringBootTest or @DataJpaTest to test repositories in-memory (H2).

Example @DataJpaTest will auto-configure an H2 DB and roll back after each test.



Exercises / Next steps (practice)
---------------------------------
1. Add findByNameIn(List<String> names) and test.

2. Implement a @Query that returns DTO (use interface projection or constructor expression).

3. Add @DataJpaTest tests for derived queries and JPQL.

4. Implement a custom repository (RepositoryImpl) for a complex query that uses EntityManager.

5. Try saveAll() with a large list and observe SQL batching (configure spring.jpa.properties.
hibernate.jdbc.batch_size).



Quick checklist
---------------
1. Create new Spring Starter Project (as above).
2. Add model, repo, controller classes in com.munna.springboot.day9.
3. Add application.properties and enable H2 console.
4. Run app, visit http://localhost:8080/h2-console and http://localhost:8080/api/products.
5. Test derived queries, JPQL and native endpoints.
 
 
*/





