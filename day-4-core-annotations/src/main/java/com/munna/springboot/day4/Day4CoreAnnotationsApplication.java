package com.munna.springboot.day4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day4CoreAnnotationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day4CoreAnnotationsApplication.class, args);
	}

}



/*
Project Structure
------------------

src/main/java/com/munna/springboot/day4
 ├── Day4CoreAnnotationsApplication.java
 ├── controller
 │     └── GreetingController.java
 ├── service
 │     ├── GreetingService.java
 │     ├── FancyGreetingService.java
 │     ├── DefaultGreetingService.java
 │     └── SimpleGreetingService.java
 └── repository
       └── GreetingRepository.java


1.Main Class
------------
What happens here?

@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan.

Component Scan → Spring scans your base package (com.munna.springboot.day4) for @Component, 
@Service, @Repository, @Controller.

All those classes are registered as Spring Beans inside the ApplicationContext (Spring’s container).


2.Service Layer
---------------
a) GreetingService (as a class)

	@Service marks this class as a business service.
	Automatically becomes a bean (Spring manages its lifecycle).
	You can now @Autowired it anywhere.


b) FancyGreetingService

	Another @Service bean, but with a custom name = "fancyService".
	Overrides greet() to return a different message.
	If multiple beans exist, you need @Qualifier("fancyService") to tell Spring which one to inject.
	
	
c) DefaultGreetingService
-------------------------
	Another implementation, marked with @Primary.

	Spring will inject this by default whenever multiple beans of the same type exist.
	
	
d) SimpleGreetingService
------------------------
	Example of a @Component.

	Equivalent to @Service, but more generic (can be used for utilities, helpers, etc.).

	This is not tied to service/repository semantics — just a simple bean.
	
	
3. Repository Layer
-------------------
	@Repository is a specialization of @Component.

	Normally used for DAO / database operations.

	Here, just returns a static string.

	Spring also applies exception translation to @Repository beans when working with DB.
	
	
	
4. Controller Layer
-------------------

Key points:

@Controller registers this class as a Web Controller.

@GetMapping("/hello") → handles HTTP GET request for /hello.

@ResponseBody → response is directly returned as string instead of view 
	(same effect as @RestController).

@Autowired → Spring injects beans automatically.
	greetingService → gets @Primary DefaultGreetingService.
	fancyGreetingService → gets FancyGreetingService via @Qualifier("fancyService").
	greetingRepository → gets the GreetingRepository bean.
	
		
		
5. How the Flow Works
---------------------
1. You run Day4CoreAnnotationsApplication.

2. Spring scans all classes → registers beans.

3. You call http://localhost:8080/hello.
	Controller → greetingService.greet() → returns DefaultGreetingService message.

4. You call http://localhost:8080/fancy.
	Controller → fancyGreetingService.greet() → returns FancyGreetingService message.

5. You call http://localhost:8080/repo.
	Controller → greetingRepository.getMessage() → returns repository message.		




*/







