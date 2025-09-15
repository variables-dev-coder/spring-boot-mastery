package com.munna.springboot.day4.clean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day4CoreAnnotationsCleanApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day4CoreAnnotationsCleanApplication.class, args);
	}

}

/*

Explanation
-----------
Why this is cleaner:
	GreetingService is an interface → defines a contract (what a service must do).
	DefaultGreetingService and FancyGreetingService are implementations.
	@Primary ensures one implementation is chosen automatically.
	@Qualifier allows picking explicitly when needed.
	GreetingRepository shows persistence layer separation.
	GreetingController shows dependency injection and how beans are resolved.



/hello → comes from @Primary DefaultGreetingService
/fancy → comes from @Qualifier("fancyService") → FancyGreetingService
/repo → message from @Repository layer
This setup shows real-world layering:
@Controller → entry point for requests
@Service → business logic (multiple implementations possible)
@Repository → data access / persistence



*/