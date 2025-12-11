package com.munna.springboot.day17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Day17SpringbootCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day17SpringbootCachingApplication.class, args);
	}

}

/*

Caching improves performance, reduces database calls, and makes your API ultra-fast.

Spring Boot supports caching using:

	@Cacheable → Cache method results
	@CacheEvict → Remove cache entries
	@CachePut (extra) → Update cache without removing
	@EnableCaching → Activate caching in the project

Today we will build a full Employee API with caching.



 src/main/java
 └── com.munna.springboot.day17
     ├── Day17CachingApplication.java
     ├── config
     │    └── CacheConfig.java
     ├── entity
     │    └── Employee.java
     ├── repository
     │    └── EmployeeRepository.java
     ├── service
     │    └── EmployeeService.java
     ├── controller
          └── EmployeeController.java



*/