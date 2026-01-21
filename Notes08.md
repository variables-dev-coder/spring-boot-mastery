# 🚀 Spring MVC Architecture — Expert-Level Explanation (with Flow & Internals)

Spring MVC is not just controllers and annotations.

It is a request orchestration engine built around one powerful concept:
> Front Controller Pattern

Everything revolves around DispatcherServlet.

### 🧠 High-Level Mental Model

Think of Spring MVC like an airport control tower ✈️
- Clients → planes (HTTP requests)
- DispatcherServlet → air traffic controller
- Controllers → destination gates
- View Resolver → boarding pass generator

No request moves without DispatcherServlet permission.

---

## 🏗️ Core Components (Internal View)
### 1️⃣ DispatcherServlet (Front Controller)
- Single entry point for ALL HTTP requests
- Created automatically by Spring Boot
- Responsibilities:
   - Receive request
   - Find controller
   - Call controller method
   - Handle exceptions
   - Resolve view
   - Return response

💡 Without DispatcherServlet → no Spring MVC

---

### 2️⃣ Handler Mapping

> ❓ “Which controller should handle this URL?”

- Maps:
    - URL
    - HTTP method
    - Headers

- Example:

GET /users → UserController.getUsers()

Spring Boot uses:
- RequestMappingHandlerMapping (default)

---

### 3️⃣ Handler Adapter

> ❓ “How do I invoke this controller?”

Why needed?
- Spring supports different controller styles
- Adapter abstracts invocation logic

Controller never called directly — always via adapter.

---

### 4️⃣ Controller

Your business entry point.

Types:
- @Controller → View based
- @RestController → JSON/XML

Controller responsibilities:
- Validate input
- Call service layer
- Prepare response

🚫 Controller must not:
- Access DB directly
- Contain business rules
- Handle exceptions manually

---

### 5️⃣ Model
- Carries data from Controller → View
- Key-Value structure

Example:

model.addAttribute("users", userList);

---

### 6️⃣ View Resolver

> ❓ “Which view should render this response?”

Resolves logical name → actual file.

Examples:
- user → /templates/user.html
- home → /WEB-INF/jsp/home.jsp

Common resolvers:
- Thymeleaf
- JSP
- FreeMarker

⚠️ REST APIs skip this step

---

### 7️⃣ View
- Renders final output
- HTML / JSON / XML

In REST:
- HttpMessageConverter replaces view

---

### 🔄 Complete Request Flow (Step-By-Step)

Client

  ↓
  
DispatcherServlet

  ↓
  
HandlerMapping

  ↓
  
HandlerAdapter

  ↓
  
Controller

  ↓
  
Service Layer

  ↓
  
Controller returns Model/View

  ↓
  
ViewResolver

  ↓
  
View Rendering

  ↓
  
Response to Client

---

### 🧩 REST API Flow (Important Difference)

For @RestController:

❌ No ViewResolver

❌ No View

✅ HttpMessageConverter serializes response

Controller

  ↓
  
Object (User)

  ↓
  
HttpMessageConverter

  ↓
  
JSON Response

---

### ⚙️ Key Supporting Components (Advanced)
#### 🔹 Interceptors
- Pre / Post request logic
- Logging, auth, metrics

preHandle()

postHandle()

afterCompletion()

#### 🔹 Filters
- Servlet-level
- Runs before Spring

Used for:
- CORS
- Security
- Encoding

#### 🔹 Exception Handling
- @ControllerAdvice
- Centralized error handling
- @ExceptionHandler(Exception.class)

###3 🔹 Data Binding & Validation
- Converts request → Java object
- Uses:
   - @RequestBody
   - @PathVariable
   - @RequestParam
   - @Valid

### 🧪 Why Spring MVC Is Powerful

| Feature            | Reason                |
| ------------------ | --------------------- |
| Single Entry Point | Better control        |
| Loose Coupling     | Replace any layer     |
| Testability        | Mock MVC              |
| Extensibility      | Interceptors, Filters |
| REST + MVC         | Same engine           |

❌ Common Interview Traps

❓ Is Controller called directly?

👉 No — DispatcherServlet controls everything

❓ Is Spring MVC REST only?

👉 No — supports MVC + REST

❓ Is DispatcherServlet optional?

👉 No — mandatory

---

### 🧠 Expert Insight (Interview Gold)
> Spring MVC is not about annotations.
> 
> It is about request orchestration via Front Controller + Strategy Pattern.

Patterns used:
- Front Controller
- Adapter
- Strategy
- Template Method

---

# ✅ Spring MVC Interview Q&A (Backend / REST Focus)
1️⃣ What is Spring MVC?

Answer:
Spring MVC is a request-handling web framework based on the Front Controller pattern.
It centralizes all HTTP requests through DispatcherServlet, which routes requests to controllers, handles validation, exceptions, and produces responses (HTML or JSON).

---

2️⃣ Is Spring MVC only for JSP applications?

Answer:
❌ No.
Spring MVC is independent of JSP.
In modern applications, Spring MVC is used mainly for REST APIs, where JSON acts as the View and HttpMessageConverter replaces ViewResolver.

---

3️⃣ Is Spring MVC legacy or still in demand?

Answer:
Spring MVC is not legacy.
JSP-based MVC is legacy, but REST-based Spring MVC is highly demanding and forms the backbone of Spring Boot microservices.

---

4️⃣ Explain Spring MVC architecture briefly.

Answer:
Spring MVC follows this flow:

Client → DispatcherServlet → HandlerMapping
→ HandlerAdapter → Controller → Service
→ (JSON via HttpMessageConverter) → Client

DispatcherServlet controls the entire request lifecycle.

---

5️⃣ What is DispatcherServlet?

Answer:
DispatcherServlet is the front controller of Spring MVC.

Responsibilities:
- Receives all HTTP requests
- Finds the correct controller
- Invokes controller methods
- Handles exceptions
- Returns response

Without it, Spring MVC does not work.

---

6️⃣ How does Spring MVC find the correct controller?

Answer:
Using HandlerMapping, which maps:
- URL
- HTTP method
- Headers

Example:

@GetMapping("/users")

Mapped internally by RequestMappingHandlerMapping.

---

7️⃣ What is HandlerAdapter and why is it needed?

Answer:
HandlerAdapter acts as a bridge between DispatcherServlet and Controller.

Why needed:
- Supports multiple controller styles
- Abstracts controller invocation logic

Controller methods are never called directly.

---

8️⃣ Difference between @Controller and @RestController?

| Aspect       | @Controller    | @RestController |
| ------------ | -------------- | --------------- |
| Purpose      | View rendering | REST APIs       |
| Response     | JSP/HTML       | JSON/XML        |
| ViewResolver | Used           | Not used        |

@RestController = @Controller + @ResponseBody

---

9️⃣ Where is MVC in REST APIs?

Answer:

| MVC Part   | REST Project    |
| ---------- | --------------- |
| Model      | Entity / DTO    |
| View       | JSON            |
| Controller | @RestController |

👉 JSON is the View

---

🔟 What replaces ViewResolver in REST APIs?

Answer:
HttpMessageConverter

It converts:
- Java Object → JSON
- JSON → Java Object

Common converters:
- Jackson
- Gson

---

1️⃣1️⃣ What is the role of Model in REST?

Answer:
In REST, the model is represented by:
- Entity
- DTO

Data is returned directly as JSON instead of being forwarded to a JSP.

---

1️⃣2️⃣ Filters vs Interceptors?

| Feature  | Filter         | Interceptor   |
| -------- | -------------- | ------------- |
| Level    | Servlet        | Spring MVC    |
| Runs     | Before Spring  | Inside Spring |
| Use case | Security, CORS | Logging, Auth |

Filters run before DispatcherServlet.

---

1️⃣3️⃣ How does exception handling work in Spring MVC?

Answer:
Using:
- @ControllerAdvice
- @ExceptionHandler

It provides centralized error handling across all controllers.

---

1️⃣4️⃣ Is Spring MVC synchronous or asynchronous?

Answer:
Spring MVC is synchronous by default.
It can support async processing using:
- Callable
- DeferredResult

But core MVC remains synchronous.

---

1️⃣5️⃣ How does validation work in Spring MVC?

Answer:
Uses:
- @Valid
- Bean Validation (Hibernate Validator)

Example:

@PostMapping
public ResponseEntity<?> save(@Valid @RequestBody User user)

Validation happens before controller method execution.

---

1️⃣6️⃣ Is Spring Boot different from Spring MVC?

Answer:
Spring Boot is not a replacement for Spring MVC.
- Spring MVC → framework
- Spring Boot → auto-configuration + setup simplification

Spring Boot uses Spring MVC internally.

---

1️⃣7️⃣ Can a project be backend-only and still use MVC?

Answer:
✅ Yes.
Most modern projects are backend-only Spring MVC REST APIs.

No JSP

No HTML

Only JSON responses

---

1️⃣8️⃣ What design patterns are used in Spring MVC?

Answer:
- Front Controller
- Adapter
- Strategy
- Template Method

---

1️⃣9️⃣ How would you explain Spring MVC in one line (Interview Killer)?

Answer:
> Spring MVC is a front-controller–based framework that manages the entire HTTP request lifecycle and supports both REST APIs and traditional MVC applications.

---

2️⃣0️⃣ How do you know your CRUD projects use Spring MVC?

Answer:
If your project has:
- @RestController
- DispatcherServlet
- Request mappings

Then it is Spring MVC internally, even if you never wrote JSP.

---

### 🎯 Final Interview Tip (Very Important)

When asked about MVC, never talk only about JSP.
Always talk about:
- DispatcherServlet
- Request lifecycle
- REST flow
- JSON as View

---







































