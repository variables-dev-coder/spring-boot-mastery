package com.munna.springboot.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

}

/*
 
Mini Project Completed Successfully
===================================

✔ Full CRUD
✔ MySQL Database
✔ Service Layer
✔ Controller
✔ Data Persistence
✔ Clean architecture
✔ Professional code structure


#MASTER NOTES — Employee Management System (Spring Boot + MySQL)

(Everything explained in simple, clear, interview-ready manner)

1. Project Overview

This mini-project performs CRUD Operations on Employee data using:

✔ Spring Boot
✔ Spring Data JPA
✔ MySQL
✔ REST API architecture
✔ Service Layer Pattern
✔ Lombok
✔ Hibernate ORM

This project is perfect for interviews because it covers:
	Controller
	Service
	Repository
	Entity
	Database Layer
	JPA & Transactions
	Validation
	Auto table creation
	Dependency Injection
	

2. Database Flow

Spring Boot → JPA → Hibernate → MySQL

Entity → Table
Repository → SQL Generator
Service → Business Logic
Controller → REST Endpoints


3. CRUD APIs Implemented
➤ POST /api/employees

Create Employee

➤ GET /api/employees

Get all employees

➤ GET /api/employees/{id}

Get employee by ID

➤ PUT /api/employees/{id}

Update employee

➤ DELETE /api/employees/{id}

Delete employee


4. application.properties Explanation

spring.datasource.url=jdbc:mysql://localhost:3306/ems_db
spring.datasource.username=root
spring.datasource.password=12345

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


Meaning:

ddl-auto=update → Auto-create/Update tables

show-sql=true → Print SQL queries

format_sql=true → Pretty formatting


5. Complete Project Folder Structure

com.munna.springboot.ems
│
├─ controller
│     └─ EmployeeController.java
│
├─ service
│     ├─ EmployeeService.java
│     └─ impl
│          └─ EmployeeServiceImpl.java
│
├─ repository
│     └─ EmployeeRepository.java
│
├─ model
│     └─ Employee.java
│
└─ EmployeeManagementSystemApplication.java


6. Important Concepts Used in Project
✔ REST Architecture
✔ JPA Repository
✔ Dependency Injection
✔ Layered Architecture
✔ Hibernate ORM
✔ Service Layer Pattern
✔ Exception Handling (optional)
✔ DTO (optional)
✔ MySQL persistence


1. What is the architecture used in this project?

Answer:
Layered Architecture (Controller → Service → Repository → Database)
	Controller handles HTTP requests
	Service contains business logic
	Repository communicates with DB using JPA
	Entity maps Java objects to database tables

 
2. What is the role of @RestController?

Answer:
@RestController = @Controller + @ResponseBody
It returns JSON responses directly without using JSP/views.



3. Why do we use a Service Layer?

Answer:
To separate business logic from controller logic.
Controller should only handle API requests, not logic.


4. What is @Autowired or @RequiredArgsConstructor used for?

Answer:
Used for Dependency Injection.
It injects the required bean (like service or repository) automatically.


5. Why do we use interface + impl in service?

Answer:
	Allows flexibility
	Good for testing
	Easy to replace implementation
	Follows industry standards (SOLID principles)
	

6. What is JpaRepository? Why not use CrudRepository?

JpaRepository provides:
	Pagination
	Sorting
	Batch operations
	More advanced methods
CrudRepository provides only basic CRUD.
JpaRepository = Everything + Extra Features.


7. What is @Entity in Spring?

Answer:
It marks a class as a database table.
Fields = Table Columns.


8. What happens when we run the Spring Boot app?

Hibernate does:
	1.Connect to MySQL
	2.Read Entity classes
	3.Create/Update tables (ddl-auto=update)
	4.Setup repositories
	5.Start embedded Tomcat server


9. Why did you choose ddl-auto=update?

Best for development.
It automatically:
	Creates tables
	Adds new columns
	Does NOT delete data


10. What if I want to generate SQL queries manually?

Use:

hibernate.hbm2ddl.auto=none

This will not auto-create any tables.



11. What is the difference between @GetMapping and @PostMapping?

@GetMapping used to fetch data
@PostMapping used to create new data
@PutMapping updates
@DeleteMapping removes data



12. Why is your Controller failing with NullPointerException?

Because Spring could not inject the service bean.
Solution: Add:

@RequiredArgsConstructor
private final EmployeeService service;

@Autowired


13. Why did table name become employee instead of employees?

Because of:

@Table(name = "employee")

or default table naming.
Must manually specify to fix.


14. What is the return type of Repository methods?

save() → returns saved entity
findAll() → returns List
findById() → Optional
deleteById() → void


15. Why Optional in findById()?

To avoid NullPointerException.
It forces us to handle missing records safely.


16. How do you handle exceptions in this project?

Use:
	@RestControllerAdvice
	Custom exception class
	@ExceptionHandler

E.g. EmployeeNotFoundException


17. How can you convert Entity to DTO?

Using:
	ModelMapper
	MapStruct
	Manual mapping
	

18. What is the difference between JPA and Hibernate?

	JPA → Specification (rules)
	Hibernate → Implementation of JPA
	
	
19. How does Hibernate generate SQL automatically?

Using ORM mapping from Entity annotations.


20. How do you test APIs?

Using Postman.
Steps:
	1.Hit POST → insert
	2.Hit GET → fetch
	3.Hit PUT → update
	4.Hit DELETE → remove
	
	

*/