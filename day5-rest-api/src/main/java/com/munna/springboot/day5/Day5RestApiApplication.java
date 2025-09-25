package com.munna.springboot.day5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day5RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day5RestApiApplication.class, args);
	}

}

/*

What this does:
===============
	@RestController → Marks this as REST API controller.
	@RequestMapping("/users") → All APIs will start with /users.
	@PostMapping("/add") → Handles POST requests to /users/add.
	@RequestBody User user → Accepts JSON input and converts it into User object.
	

	@GetMapping("/{id}") → Example: /users/1
	@PathVariable int id → Captures the id from the URL.
	Returns the User object stored in the HashMap.
	
	
	@GetMapping("/all") → Example: /users/all
	Returns the whole userMap → all stored users.
	
	
	@PutMapping("/update/{id}") → Handles PUT requests like /users/update/1
	@PathVariable int id → Get the ID from URL
	@RequestBody User user → Get JSON data for updated info
	Checks if user exists → updates name/email
	
	@DeleteMapping("/delete/{id}") → Handles DELETE requests
	@PathVariable int id → Get the ID from URL
	Checks if user exists → removes from HashMap
	
	Day 5 REST API Project is now fully complete!
	POST /users/add → Add user
	GET /users/{id} → Get user by ID
	GET /users/all → Get all users
	PUT /users/update/{id} → Update user
	DELETE /users/delete/{id} → Delete user
	
	
	
	1️. @RestController
		Combines @Controller + @ResponseBody.
		Every method returns data directly (JSON/XML), not a view.
		
	Example:

	@RestController
	@RequestMapping("/users")
	public class UserController { ... }

	Why we use it: Simplifies creating REST APIs in Spring Boot.


	2. @RequestMapping
		Defines base URL path for controller or method.

	Example:

	@RequestMapping("/users")
	All methods inside controller will start with /users.
	
	
	3. @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
		Shortcut for @RequestMapping(method = RequestMethod.GET/POST/PUT/DELETE)

	Example:

	@GetMapping("/{id}")      // GET /users/1
	@PostMapping("/add")     // POST /users/add
	@PutMapping("/update/{id}")  // PUT /users/update/1
	@DeleteMapping("/delete/{id}") // DELETE /users/delete/1

	Makes code cleaner and readable.
	
	
	4. @PathVariable
		Binds URL path segment to method parameter.

	Example:
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) { ... }

	URL /users/1 → id = 1
	
	
	5. @RequestBody
		Binds HTTP request body JSON to Java object.

	Example:
	
	@PostMapping("/add")
	public String addUser(@RequestBody User user) { ... }

	Sends JSON like:
	
	{
  	"id": 1,
  	"name": "Munna",
  	"email": "munna@example.com"
	}

	Spring automatically converts it into User object.
	
	
	
	6. HashMap for In-Memory CRUD
		We use Map<Integer, User> as temporary DB.
		Key → user ID, Value → User object
		Simple, fast, no database required for practice.
		
		
Operations:

| CRUD   | HashMap method         |
| ------ | ---------------------- |
| Create | `put(id, user)`        |
| Read   | `get(id)` or iterate   |
| Update | `get(id)` + set fields |
| Delete | `remove(id)`           |
		
	
	
	Day 5: REST API Basics – Interview Questions & Answers
	=======================================================
	
	1. What is @RestController?

Answer:

@RestController is a specialized version of @Controller that combines @Controller + @ResponseBody.

It tells Spring to return data (JSON/XML) directly instead of rendering a view.



2. Difference between @Controller and @RestController

| @Controller                       | @RestController                     |
| --------------------------------- | ----------------------------------- |
| Returns view (HTML/JSP)           | Returns data (JSON/XML)             |
| Used with templates               | Used in REST APIs                   |
| Must use `@ResponseBody` for data | Implicitly includes `@ResponseBody` |


3. What is @RequestMapping?

Defines URL for controller/method.

Can specify HTTP method using method = RequestMethod.GET/POST/...

Base path example: @RequestMapping("/users")


4. Difference between @GetMapping, @PostMapping, @PutMapping, @DeleteMapping and @RequestMapping

	@GetMapping → Shortcut for @RequestMapping(method = RequestMethod.GET)

	Similar for POST, PUT, DELETE

	Cleaner and more readable than generic @RequestMapping.
	
	
	
5. What is @PathVariable?

Binds a value from the URL path to a method parameter.

Example: /users/1 → @PathVariable int id → id = 1



6. What is @RequestBody?

Binds HTTP request body (JSON) to a Java object.

Example: POST JSON → User user object automatically created




7. How do you store data without a database?

Use in-memory HashMap<Integer, User>

Key → ID, Value → Object

Useful for practice/testing CRUD without DB


8. Explain a simple CRUD flow in Spring Boot

Create: POST /users/add → store in HashMap

Read: GET /users/{id} → fetch from HashMap

Update: PUT /users/update/{id} → modify fields

Delete: DELETE /users/delete/{id} → remove from HashMap



9. What is the default port of Spring Boot?

8080 (can change in application.properties)


10. How to change port in application.properties?

server.port=9090

App will run on port 9090 instead of 8080

======================================================



1. Exercise 1 – Hello REST API

Task: Create a GET endpoint /hello returning "Hello World!".

Solution:

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
Test: GET /api/hello → "Hello World!"


2. Exercise 2 – Add Two Numbers via RequestParam

Task: GET /add?a=5&b=3 → return sum.

Solution:

@GetMapping("/add")
public int add(@RequestParam int a, @RequestParam int b) {
    return a + b;
}
Test: GET /add?a=5&b=3 → 8



3. Exercise 3 – PathVariable Example

Task: GET /square/4 → return 16

Solution:

@GetMapping("/square/{num}")
public int square(@PathVariable int num) {
    return num * num;
}



4. Exercise 4 – POST JSON Object

Task: POST /user with JSON { "id":1,"name":"Munna" } → return "User added: Munna"

Solution:

@PostMapping("/user")
public String addUser(@RequestBody User user) {
    return "User added: " + user.getName();
}



5. Exercise 5 – GET User by ID (HashMap)

Task: In-memory storage, GET /users/1 → return user

Solution:

private Map<Integer, User> userMap = new HashMap<>();

@GetMapping("/users/{id}")
public User getUser(@PathVariable int id) {
    return userMap.get(id);
}



Day 5 – Medium REST API Exercises (6–10)
========================================

6. Exercise 6 – GET All Users

@GetMapping("/users")
public Collection<User> getAllUsers() {
    return userMap.values();
}


7. Exercise 7 – Update User

@PutMapping("/users/{id}")
public String updateUser(@PathVariable int id, @RequestBody User user) {
    if(userMap.containsKey(id)){
        userMap.put(id, user);
        return "User updated with ID: " + id;
    }
    return "User not found";
}


8. Exercise 8 – Delete User

@DeleteMapping("/users/{id}")
public String deleteUser(@PathVariable int id) {
    if(userMap.containsKey(id)){
        userMap.remove(id);
        return "Deleted user with ID: " + id;
    }
    return "User not found";
}


9. Exercise 9 – Find Users by Name (RequestParam)

@GetMapping("/find")
public List<User> findByName(@RequestParam String name) {
    return userMap.values().stream()
        .filter(u -> u.getName().equalsIgnoreCase(name))
        .toList();
}


10. Exercise 10 – Count Users

@GetMapping("/count")
public int countUsers() {
    return userMap.size();
}



*/





