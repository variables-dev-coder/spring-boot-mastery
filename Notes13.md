# Spring Boot Day 40 🔥
## Exception handling with @ControllerAdvice

---

### 1️⃣ Why Exception Handling Matters in Real Systems

In real applications, things WILL fail:
- Invalid user input
- Missing records in DB
- Null values
- External API failures
- Authorization issues
- Unexpected bugs

❌ If you don’t handle exceptions properly:
- Users see 500 Internal Server Error
- Error responses are inconsistent
- Logs become useless
- Debugging becomes painful
- Your API looks unprofessional

✅ Proper exception handling gives:
- Clean, predictable API responses
- Centralized error logic
- Better debugging & logging
- Separation of business logic from error logic

---

### 2️⃣ The Naive Approach (What NOT to Do)

#### Try–Catch inside every controller ❌

@GetMapping("/users/{id}")

public ResponseEntity<User> getUser(@PathVariable Long id) {

    try {
        return ResponseEntity.ok(userService.getUser(id));
    } catch (Exception e) {
        return ResponseEntity.status(500).build();
    }
}

#### Problems:
- Repeated code everywhere
- Controllers become messy
- No standard error response
- Hard to maintain

This is anti-Spring philosophy.

----

### 3️⃣ How Spring Boot Wants You to Handle Exceptions

Spring follows Separation of Concerns.
- Controllers → handle requests
- Services → business logic
- Exception Handlers → error handling

This is where @ControllerAdvice comes in.

---

#### 4️⃣ What is @ControllerAdvice (Core Concept)

@ControllerAdvice is a global exception handling mechanism in Spring Framework.

Think of it as:
> A centralized error interceptor for controllers

- It listens for exceptions thrown by any controller
- Catches them
- Converts them into clean HTTP responses

---

### 5️⃣ How Exception Flow Works (Very Important)
#### Request Flow:

Client → Controller → Service → Repository

                     ↓
                 Exception thrown
                     ↓
            @ControllerAdvice catches it
                     ↓
             Custom HTTP response


⚠️ Controllers do NOT catch exceptions

They throw them, intentionally.

---

### 6️⃣ Basic Structure of @ControllerAdvice

@ControllerAdvice

public class GlobalExceptionHandler {

}

This class becomes active automatically.

---

### 7️⃣ @ExceptionHandler – The Heart of It

Inside @ControllerAdvice, we define handlers.

@ExceptionHandler(Exception.class)

public ResponseEntity<String> handleException(Exception ex) {

    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.getMessage());
}

#### What’s happening:
- Exception.class → which exception to catch
- Method runs only when that exception occurs
- Returns HTTP response


---

### 8️⃣ Handling Specific Exceptions (Best Practice)

Never rely only on Exception.class.

#### Example: Resource Not Found

@ExceptionHandler(ResourceNotFoundException.class)

public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
}

#### Why this is powerful:
- Clear error meaning
- Correct HTTP status
- Easy for frontend to understand

----

### 9️⃣ Custom Exception Classes (Real-World Style)

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

#### Why RuntimeException?
- Spring automatically propagates it
- No forced try–catch
- Cleaner service code

---

### 🔟 Throwing Exceptions from Service Layer

public User getUser(Long id) {

    return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
}


✅ Service focuses on business logic

✅ Error handling is centralized


---

### 1️⃣1️⃣ Standard Error Response Object (Senior Level)

Instead of plain strings, return structured errors.

public class ErrorResponse {

    private int status;
    private String message;
    private long timestamp;
}

#### Handler Example:

@ExceptionHandler(ResourceNotFoundException.class)

public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {

    ErrorResponse error = new ErrorResponse(
        404,
        ex.getMessage(),
        System.currentTimeMillis()
    );
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}

🎯 This is production-grade API design.

---

### 1️⃣2️⃣ @RestControllerAdvice (Very Important)

If you are building REST APIs, use:

@RestControllerAdvice

Why?
- Combines @ControllerAdvice + @ResponseBody
- Automatically returns JSON
- No need to annotate each method

👉 Always prefer @RestControllerAdvice in APIs

---

### 1️⃣3️⃣ Handling Validation Errors (Common Interview Topic)

Example: @Valid fails

@ExceptionHandler(MethodArgumentNotValidException.class)

public ResponseEntity<Map<String, String>> handleValidationErrors(

        MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(
            error.getField(), error.getDefaultMessage()
        ));

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}

This gives field-wise errors, perfect for frontend.

---

### 1️⃣4️⃣ Ordering Multiple Exception Handlers

Spring matches most specific exception first.

Order:
- Custom exceptions
- Framework exceptions
- Generic Exception.class (last)

⚠️ Always keep generic handler at the bottom

---

### 1️⃣5️⃣ Why @ControllerAdvice is a MUST in Interviews

Interviewers look for:
- Clean architecture
- Centralized error handling
- Custom exceptions
- Meaningful HTTP status codes
- Clean JSON error responses

If you explain this well → strong senior-level impression 💯

---

### 🔥 Final Mental Model (Remember This)
> Controllers throw, Services decide, ControllerAdvice responds


# Spring Boot Exception Handling – Interview Q&A

## 1. What is exception handling in Spring Boot?
Exception handling in Spring Boot is the mechanism to handle runtime and application errors gracefully and convert them into meaningful HTTP responses instead of exposing stack traces or returning generic 500 errors.

---

## 2. What is @ControllerAdvice in Spring Boot?
@ControllerAdvice is a specialization of @Component that allows global exception handling across all controllers. It centralizes error-handling logic and separates it from business logic.

---

## 3. How does @ControllerAdvice work internally?
When an exception is thrown from any controller method, Spring scans for a matching @ExceptionHandler method inside @ControllerAdvice. If found, it executes that method and returns the defined HTTP response.

---

## 4. What is @ExceptionHandler?
@ExceptionHandler is used to define a method that handles a specific type of exception. It maps exceptions to handler methods within a controller or @ControllerAdvice class.

---

## 5. Difference between @ControllerAdvice and @ExceptionHandler?
- @ControllerAdvice → Defines global scope for exception handling  
- @ExceptionHandler → Handles a specific exception type  

They are usually used together.

---

## 6. What is @RestControllerAdvice?
@RestControllerAdvice is a combination of @ControllerAdvice and @ResponseBody.  
It is used in REST APIs to automatically return JSON responses without adding @ResponseBody to every handler method.

---

## 7. Why should we avoid try-catch blocks in controllers?
- Leads to code duplication
- Breaks separation of concerns
- Makes controllers bulky
- Hard to maintain

Spring encourages centralized exception handling using @ControllerAdvice.

---

## 8. Why do we create custom exceptions?
Custom exceptions:
- Improve readability
- Represent business-specific errors
- Enable proper HTTP status mapping
- Improve debugging and logging

---

## 9. Why do custom exceptions extend RuntimeException?
RuntimeException:
- Does not require explicit try-catch
- Automatically propagates to Spring’s exception resolver
- Keeps service code clean

---

## 10. Where should exceptions be thrown in a layered architecture?
Exceptions should be thrown from:
- Service layer (business rules)
- Repository layer (data access issues)

Controllers should only delegate and not handle exceptions.

---

## 11. What is the flow of exception handling in Spring Boot?
Client → Controller → Service → Exception thrown → @ControllerAdvice → HTTP Response

---

## 12. How do you return custom HTTP status codes?
Using ResponseEntity with HttpStatus inside @ExceptionHandler methods.

---

## 13. What happens if no @ExceptionHandler is found?
Spring returns a default error response with:
- HTTP 500 status
- Stack trace (disabled in production by default)

---

## 14. How do you handle validation errors in Spring Boot?
By handling MethodArgumentNotValidException inside @ControllerAdvice and extracting field-level validation errors.

---

## 15. What is MethodArgumentNotValidException?
It is thrown when request body validation fails for @Valid annotated objects in controller methods.

---

## 16. How do you send structured error responses?
By creating a custom ErrorResponse class containing:
- status
- message
- timestamp
- path (optional)

---

## 17. Can multiple @ControllerAdvice classes exist?
Yes. Multiple @ControllerAdvice classes can exist and can be prioritized using @Order annotation.

---

## 18. How does Spring decide which exception handler to execute?
Spring chooses the most specific matching exception handler first. Generic Exception handlers are considered last.

---

## 19. Why should Exception.class handler be placed at the bottom?
Because it catches all exceptions. If placed earlier, it will block execution of specific exception handlers.

---

## 20. Can @ControllerAdvice handle exceptions from filters or interceptors?
No. @ControllerAdvice handles exceptions only from controllers.  
Exceptions from filters must be handled inside the filter itself.

---

## 21. Difference between @ResponseStatus and ResponseEntity?
@ResponseStatus:
- Static HTTP status
- No dynamic response body

ResponseEntity:
- Dynamic status
- Custom headers
- Flexible response body

---

## 22. What are best practices for exception handling in Spring Boot?
- Use @RestControllerAdvice
- Create custom exceptions
- Return structured error responses
- Avoid try-catch in controllers
- Log exceptions centrally
- Map correct HTTP status codes

---

## 23. Is exception handling important for microservices?
Yes. Consistent error responses are critical for:
- API consumers
- Inter-service communication
- Debugging and monitoring

---

## 24. How do you log exceptions properly?
By logging inside @ExceptionHandler methods using logging frameworks like SLF4J and Logback.

---

## 25. Why is centralized exception handling considered a clean architecture practice?
Because it:
- Enforces separation of concerns
- Improves maintainability
- Reduces code duplication
- Enhances readability and scalability


---

### 🔹 Scenario 1: Record Not Found (Most Common)
#### ❓ Interviewer:
> Suppose a user requests /users/101 but the user doesn’t exist. How do you handle this?

#### ✅ Expected Real-Time Answer:

In the service layer, I throw a custom ResourceNotFoundException.
This exception is globally handled using @RestControllerAdvice, which returns a 404 NOT FOUND response with a meaningful message.

#### 💡 Why this impresses:
- Correct HTTP semantics
- Service-layer responsibility
- Centralized handling

###🔹 Scenario 2: Validation Failure from Client
#### ❓ Interviewer:
> If a client sends invalid data in request body, how do you return field-level errors?

#### ✅ Real-World Answer:

I use @Valid on the request DTO.
When validation fails, Spring throws MethodArgumentNotValidException.
I handle it inside @ControllerAdvice and extract field errors into a map so the frontend can display exact error messages.

#### 💬 Bonus Line:
> “This avoids generic error messages and improves frontend UX.”

### 🔹 Scenario 3: Duplicate Record (Business Rule Violation)
#### ❓ Interviewer:

What if someone tries to register using an already existing email?

#### ✅ Senior Answer:

I check for email existence in the service layer.
If it already exists, I throw a custom DuplicateResourceException.
@ControllerAdvice catches it and returns 409 CONFLICT.

#### 🎯 Why 409?

Because the request is valid, but it violates a business constraint.

###🔹 Scenario 4: Database Down / Unexpected Error
#### ❓ Interviewer:

What happens if the database goes down suddenly?

#### ✅ Real-Time Answer:

Such exceptions propagate as runtime exceptions.
I handle them using a generic Exception.class handler at the bottom of @ControllerAdvice, returning 500 INTERNAL SERVER ERROR with a safe message.

#### ⚠️ Important:

Never expose DB or stack trace details to clients.

### 🔹 Scenario 5: Multiple Controllers, Same Error Handling
#### ❓ Interviewer:

You have 20 controllers. Do you write exception handling in all of them?

#### ✅ Correct Answer:

No.
I use @RestControllerAdvice to handle exceptions globally, so all controllers automatically share the same error-handling logic.

#### 💬 Interviewer hears:

👉 Clean architecture

### 🔹 Scenario 6: REST API vs MVC Application
#### ❓ Interviewer:

When do you use @ControllerAdvice vs @RestControllerAdvice?

#### ✅ Expert Answer:

- REST APIs → @RestControllerAdvice (JSON response)
- MVC apps → @ControllerAdvice (views / pages)

Using the wrong one leads to incorrect response formats.

### 🔹 Scenario 7: Exception in Filter or Interceptor
#### ❓ Interviewer:
> Will @ControllerAdvice handle exceptions from filters?

#### ✅ Strong Answer:

No.
@ControllerAdvice handles only controller-layer exceptions.
Exceptions in filters must be handled inside the filter itself.

👉 This answer instantly signals deep framework knowledge.

### 🔹 Scenario 8: Multiple Exception Handlers Exist
#### ❓ Interviewer:

If two handlers can catch the same exception, which one runs?

### ✅ Correct Answer:

Spring selects the most specific exception handler first.
Generic handlers like Exception.class should always be placed last.

### 🔹 Scenario 9: Logging Strategy
#### ❓ Interviewer:

Where do you log exceptions?

#### ✅ Real-World Answer:

I log exceptions inside @ExceptionHandler methods using SLF4J.
This ensures centralized logging and avoids duplicate logs across layers.

### 🔹 Scenario 10: Microservices Communication
#### ❓ Interviewer:

Why is exception handling important in microservices?

#### ✅ Senior-Level Answer:

Because services communicate via APIs.

Consistent error structures help:
- Other services understand failures
- Retry logic
- Circuit breakers
- Debugging production issues

---

### 🔥 GOLDEN INTERVIEW STATEMENT (Memorize)
> “Controllers delegate, services decide, ControllerAdvice responds.”

Say this once — interviewer nods automatically

---

# Spring Boot Exception Handling – Real-Time Interview Scenarios

## Scenario 1: Resource Not Found

### Question:
A client requests a resource that does not exist. How do you handle it?

### Answer:
I throw a custom `ResourceNotFoundException` from the service layer.  
Using `@RestControllerAdvice`, I catch the exception globally and return a **404 NOT FOUND** response with a meaningful error message.

---

## Scenario 2: Validation Failure from Client

### Question:
How do you handle invalid request body data?

### Answer:
I use `@Valid` on the request DTO.  
When validation fails, Spring throws `MethodArgumentNotValidException`.  
This exception is handled in `@ControllerAdvice` to return field-wise validation errors with **400 BAD REQUEST**.

---

## Scenario 3: Duplicate Resource Creation

### Question:
How do you handle duplicate records like existing email or username?

### Answer:
I validate the business rule in the service layer.  
If a duplicate exists, I throw a `DuplicateResourceException`.  
The global exception handler returns **409 CONFLICT**.

---

## Scenario 4: Database Failure or Unexpected Error

### Question:
What happens if the database goes down?

### Answer:
Such issues throw runtime exceptions.  
I handle them using a generic `Exception.class` handler placed at the bottom of `@ControllerAdvice`, returning **500 INTERNAL SERVER ERROR** with a safe message.

---

## Scenario 5: Multiple Controllers Sharing Exception Logic

### Question:
How do you avoid writing exception handling in every controller?

### Answer:
I use `@RestControllerAdvice` to centralize exception handling, allowing all controllers to reuse the same error-handling logic.

---

## Scenario 6: REST API vs MVC Application

### Question:
When do you use `@ControllerAdvice` vs `@RestControllerAdvice`?

### Answer:
- REST APIs → `@RestControllerAdvice` for JSON responses  
- MVC applications → `@ControllerAdvice` for page-based responses

---

## Scenario 7: Exception Thrown in Filter or Interceptor

### Question:
Can `@ControllerAdvice` handle exceptions from filters?

### Answer:
No.  
`@ControllerAdvice` only handles controller-layer exceptions.  
Exceptions from filters must be handled within the filter itself.

---

## Scenario 8: Multiple Exception Handlers Exist

### Question:
How does Spring decide which exception handler to execute?

### Answer:
Spring selects the most specific exception handler first.  
Generic handlers like `Exception.class` should always be defined last.

---

## Scenario 9: Logging Exceptions

### Question:
Where should exceptions be logged?

### Answer:
Exceptions should be logged inside `@ExceptionHandler` methods to ensure centralized and consistent logging.

---

## Scenario 10: Exception Handling in Microservices

### Question:
Why is centralized exception handling important in microservices?

### Answer:
It ensures consistent error responses between services, improves inter-service communication, simplifies debugging, and supports retry and fault-tolerance mechanisms.

---

## Golden Interview Line

> Controllers delegate, services decide, ControllerAdvice responds.


---




















