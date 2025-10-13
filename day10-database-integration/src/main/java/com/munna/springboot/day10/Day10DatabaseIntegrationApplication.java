package com.munna.springboot.day10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day10DatabaseIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day10DatabaseIntegrationApplication.class, args);
	}

}


/*
 Explanation:
 
spring.datasource.url → Database connection URL
ddl-auto=update → Automatically creates/updates the table
show-sql=true → Displays SQL queries in console
MySQL8Dialect → Ensures compatibility with MySQL syntax
 

How It Works – Concept Explanation

| Layer                      | Purpose             | Explanation                                       |
| -------------------------- | ------------------- | ------------------------------------------------- |
| **Entity**                 | Represents DB table | Each object maps to a row in the `products` table |
| **Repository**             | Handles CRUD        | `JpaRepository` auto-implements methods           |
| **Service**                | Business logic      | Acts as a middle layer between controller & DB    |
| **Controller**             | REST endpoint       | Handles HTTP requests (GET, POST, DELETE)         |
| **application.properties** | Configuration       | Defines MySQL URL, credentials, JPA behavior      |

Summary

H2 → MySQL migration done
Database connection through JDBC managed by Spring Boot
CRUD operations verified
MySQL auto-syncs table using ddl-auto=update


 */
