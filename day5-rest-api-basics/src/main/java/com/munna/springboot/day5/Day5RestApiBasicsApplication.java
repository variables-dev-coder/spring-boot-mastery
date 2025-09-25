package com.munna.springboot.day5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day5RestApiBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day5RestApiBasicsApplication.class, args);
	}

}

/*

How it works — theory & why (mastery notes)
===========================================

@RestController = @Controller + @ResponseBody. Methods return body objects 
serialized to JSON by default.

@RequestMapping("/api/todos") attaches base path for controller.

@GetMapping, @PostMapping, @PutMapping, @DeleteMapping are shortcuts for 
@RequestMapping(method=...). Use them to map HTTP verbs.

@PathVariable extracts a path segment (/api/todos/{id} -> method param).

@RequestParam extracts query params (?completed=true). Useful for filters/pagination.

@RequestBody binds request JSON to a Java object. Combine with @Valid to trigger 
bean validation (requires spring-boot-starter-validation).

Use DTOs (Create/Update requests) to separate API contract from internal model. 
This prevents exposing internal entities and makes validation precise.

Thread-safety: use ConcurrentHashMap and AtomicLong for ID generation to safely 
support concurrent requests in-memory.



Return correct HTTP status codes:
---------------------------------

201 Created when a resource is created (with Location header).

200 OK for successful GET/PUT that return a body.

204 No Content for successful DELETE with no body.

400 Bad Request for validation errors.

404 Not Found when resource not exists.

Exception handling: Use @RestControllerAdvice to centralize error responses 
and return consistent JSON error shapes.


*/