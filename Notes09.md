# Spring Boot – Day 36.

## 🧠 Big Picture First (VERY IMPORTANT)

Client (Browser / Postman / Mobile App)

        |
        |  HTTP Request (GET / POST / PUT / DELETE)
        v
@RestController

        |
@RequestMapping (maps URL + HTTP method)

        |
Method executes business logic

        |
        v
HTTP Response (JSON)

Everything today is about how Spring connects HTTP → Java methods.

---

### 1️⃣ @RestController — What it REALLY means
#### ❌ Beginner explanation (not enough)
> “It creates REST APIs”

#### ✅ Expert explanation

@RestController tells Spring:
> “This class handles HTTP requests and returns data, not views.”

Internally:

@RestController = @Controller + @ResponseBody

| Annotation      | Responsibility                    |
| --------------- | --------------------------------- |
| `@Controller`   | Marks class as request handler    |
| `@ResponseBody` | Return value → HTTP response body |

So every method automatically returns JSON/XML, not JSP/HTML.

---

Example:

@RestController

public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Munna";
    }
}

What actually happens
- Spring receives HTTP request
- Calls hello()
- Converts return value → HTTP response body

📌 No Model, no ViewResolver, no JSP.

👉 Pure API

---

### ❓ Interview Question

Q: Difference between @Controller and @RestController?

Answer (perfect):

@Controller is used for MVC views (HTML/JSP),

@RestController is used for REST APIs returning data directly.

---

### 2️⃣ @RequestMapping — The Traffic Police 🚦
What problem it solves
> “Which URL + which HTTP method should call which Java method?”

#### At CLASS level

@RestController
@RequestMapping("/api/users")

public class UserController {

}

This means:

Base URL = /api/users

All methods inside will start with this path.

#### At METHOD level

@RequestMapping(value = "/all", method = RequestMethod.GET)

public List<String> getUsers() {

    return List.of("Munna", "Alex");
}

Final URL

GET /api/users/all

❗ Important Design Rule (Interview gold)

👉 Class-level mapping = resource

👉 Method-level mapping = action

Example:

/api/users        → resource

GET    /api/users → fetch users

POST   /api/users → create user

PUT    /api/users → update user

DELETE /api/users → delete user

---

### 3️⃣ HTTP Methods — NOT Just CRUD (Deep Meaning)
#### 🔹 GET
> “I want data. I will NOT change server state.”

@GetMapping("/users")

public List<User> getUsers() {}

✅ Safe

✅ Idempotent

❌ No request body (by convention)

#### 🔹 POST
> “Create something NEW.”

@PostMapping("/users")

public User createUser(@RequestBody User user) {}

❌ Not idempotent

❌ Changes server state

✅ Has request body

### 🔹 PUT
> “Replace the ENTIRE resource.”

@PutMapping("/users/{id}")

public User updateUser(@PathVariable int id, @RequestBody User user) {}

✅ Idempotent

❌ Changes server state

### 🔹 PATCH (Advanced but important)
> “Partial update”

@PatchMapping("/users/{id}")

Interviewers LOVE this if you mention it properly.

---

### 🔹 DELETE
> “Remove resource”

@DeleteMapping("/users/{id}")

public void deleteUser(@PathVariable int id) {}

✅ Idempotent

❌ Changes server state

---

### 4️⃣ Shortcut Annotations (Production Standard)

| Old Way                            | Modern Way       |
| ---------------------------------- | ---------------- |
| `@RequestMapping(method = GET)`    | `@GetMapping`    |
| `@RequestMapping(method = POST)`   | `@PostMapping`   |
| `@RequestMapping(method = PUT)`    | `@PutMapping`    |
| `@RequestMapping(method = DELETE)` | `@DeleteMapping` |

#### Clean API Example

@RestController

@RequestMapping("/api/users")

public class UserController {

    @GetMapping
    public List<User> getAllUsers() {}

    @PostMapping
    public User createUser(@RequestBody User user) {}

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,
                           @RequestBody User user) {}

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {}
}

This is industry-grade REST.

---

### 5️⃣ Common Mistakes (HR rejects for this ❌)

❌ Using POST for fetching data

❌ Returning String messages instead of proper HTTP status

❌ Mixing /getUsers, /createUser style URLs

❌ Not using proper HTTP verbs

---

### 6️⃣ Interview Power Lines (Memorize)

- “@RestController is used for RESTful APIs returning data directly.”
- “@RequestMapping maps URL + HTTP method to Java methods.”
- “HTTP methods express intent, not just CRUD.”
- “REST APIs should be resource-oriented, not action-oriented.”

---

### 7️⃣ Day 36 Mental Model (Final)

@RestController → API class

@RequestMapping → URL structure

HTTP methods    → Client intent

---


# ✅ Spring Boot Day 36 — Interview Questions & Answers

(@RestController, @RequestMapping, HTTP Methods)

---

### 1️⃣ What is @RestController?

Answer:
@RestController is used to create RESTful web services in Spring Boot.
It combines @Controller and @ResponseBody, so every method returns data directly as HTTP response instead of a view.

---

### 2️⃣ Difference between @Controller and @RestController?

| `@Controller`           | `@RestController`       |
| ----------------------- | ----------------------- |
| Returns View (JSP/HTML) | Returns Data (JSON/XML) |
| Used in MVC             | Used in REST APIs       |
| Needs `@ResponseBody`   | Auto-applied            |



---

### 3️⃣ What is @RequestMapping?

Answer:
@RequestMapping maps HTTP requests (URL + HTTP method) to Java methods.
It acts as a router between client requests and backend logic.

---

### 4️⃣ Can @RequestMapping be used at both class and method level?

Answer:
Yes.
- Class level → defines base URL
- Method level → defines specific endpoint

Final URL = class-level + method-level mapping.

---

### 5️⃣ Why do we use class-level @RequestMapping?

Answer:
To group related APIs under a common resource path, improving readability, maintainability, and REST design.

Example:

@RequestMapping("/api/users")

---

### 6️⃣ Difference between @RequestMapping and @GetMapping?

Answer:
@GetMapping is a shortcut annotation for:

@RequestMapping(method = RequestMethod.GET)

It improves readability and is preferred in modern Spring Boot applications.

---

### 7️⃣ What are HTTP methods and why are they important?

Answer:
HTTP methods define client intent.
They make APIs predictable, RESTful, and self-descriptive.

Examples:
- GET → Read data
- POST → Create data
- PUT → Update entire data
- DELETE → Remove data

---

### 8️⃣ Is GET request allowed to have a request body?

Answer:
Technically yes (HTTP spec allows), but by convention and practice, GET should not have a body.
Most servers and tools ignore it.

---

### 9️⃣ What does idempotent mean?

Answer:
An idempotent operation produces the same result no matter how many times it is executed.

Examples:
- GET → idempotent
- PUT → idempotent
- DELETE → idempotent
- POST → not idempotent

---

### 🔟 Difference between PUT and POST?

| PUT                      | POST                 |
| ------------------------ | -------------------- |
| Replaces entire resource | Creates new resource |
| Idempotent               | Not idempotent       |
| Client provides ID       | Server generates ID  |


---

### 1️⃣1️⃣ Difference between PUT and PATCH?

Answer:
- PUT → replaces the entire resource
- PATCH → updates partial fields

PATCH is preferred for partial updates.

---

### 1️⃣2️⃣ Can multiple HTTP methods map to the same URL?

Answer:
Yes.
Same URL can have different behaviors based on HTTP method.

Example:

GET    /users → fetch users

POST   /users → create user

PUT    /users → update user

DELETE /users → delete user

---

### 1️⃣3️⃣ What happens if two controller methods map to the same URL and method?

Answer:
Spring throws Ambiguous mapping exception at startup and application fails.

---

### 1️⃣4️⃣ What is the default response format of @RestController?

Answer:
JSON, if Jackson is present (default in Spring Boot).

---

### 1️⃣5️⃣ How does Spring convert Java objects to JSON?

Answer:
Using HttpMessageConverters, specifically Jackson ObjectMapper.

---

### 1️⃣6️⃣ Can we return custom HTTP status codes?

Answer:
Yes, using:
- ResponseEntity
- @ResponseStatus

Example:

return ResponseEntity.status(HttpStatus.CREATED).body(user);

---

### 1️⃣7️⃣ Why REST APIs should be resource-based and not action-based?

Answer:
REST focuses on nouns (resources), not verbs (actions).
HTTP methods already define the action.

❌ /createUser

✅ /users with POST

---

### 1️⃣8️⃣ Is @RestController mandatory for REST APIs?

Answer:
No.
You can use @Controller + @ResponseBody, but @RestController is cleaner and preferred.

---

### 1️⃣9️⃣ What is a common beginner mistake in REST APIs?

Answer:
- Using POST for fetching data
- Using action-based URLs
- Ignoring HTTP status codes
- Returning String messages instead of proper responses

---

### 2️⃣0️⃣ One-line interview summary

"@RestController handles REST APIs, @RequestMapping maps URLs, and HTTP methods express client intent."

---

### 🎯 HR-IMPRESSION TIP

If you say this line, interviewer notices you instantly 👇
> “REST APIs should be resource-oriented, HTTP-method driven, and stateless.”

---





















