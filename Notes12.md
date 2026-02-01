# ✅ Spring Boot Day 39
## Validation (@Valid, Built-in & Custom Validators)

---

### 1️⃣ Why Validation Exists (Real-World Thinking)

In real applications, bad data is more dangerous than no data.

Examples:
- Username = ""
- Age = -5
- Email = abc@
- Mobile = 123

If this reaches:
- Database ❌
- Business logic ❌
- Payment / Reports ❌

👉 Validation stops garbage at the entry gate (API layer).

---

### 2️⃣ Where Validation Happens in Spring Boot

Validation is usually applied at Controller level, before logic executes.

Flow:

Client → Controller (@Valid) → Service → Repository


If validation fails:

❌ Controller returns error

❌ Service is NOT called

This is fail-fast design.

---

### 3️⃣ What is @Valid?

@Valid tells Spring:
> “Before calling this method, validate the object using constraints defined on it.”

#### Example (High-level)

@PostMapping("/users")

public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request) {

    return ResponseEntity.ok("User created");
}

✔ @Valid triggers validation

✔ Validation rules are written inside DTO

---

### 4️⃣ Why Validation is Done on DTO (NOT Entity)

#### ❌ Bad Practice:
- Validating JPA Entity

#### ✅ Best Practice:
- Validate Request DTO

Reason:
- Entity = DB structure
- DTO = API contract
- API rules ≠ DB rules

---

### 5️⃣ Built-in Validation Annotations (MOST IMPORTANT)
#### 🔹 String Validations

| Annotation        | Purpose                               |
| ----------------- | ------------------------------------- |
| `@NotNull`        | Value must not be null                |
| `@NotBlank`       | Not null + not empty + not whitespace |
| `@NotEmpty`       | Not null + not empty                  |
| `@Size(min, max)` | Length constraint                     |
| `@Email`          | Valid email format                    |
| `@Pattern`        | Regex based validation                |

Example:

@NotBlank(message = "Name cannot be blank")

@Size(min = 3, max = 20)

private String name;

#### 🔹 Number Validations

| Annotation        | Purpose       |
| ----------------- | ------------- |
| `@Min(18)`        | Minimum value |
| `@Max(60)`        | Maximum value |
| `@Positive`       | > 0           |
| `@PositiveOrZero` | ≥ 0           |
| `@Negative`       | < 0           |

Example:

@Min(18)

@Max(60)

private int age;

#### 🔹 Date Validations

@Past        // DOB

@Future      // Delivery date

@FutureOrPresent

---

### 6️⃣ Full DTO Example (Real Project Style)

public class UserRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30)
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    private String mobile;

    @Min(value = 18, message = "Age must be >= 18")
    private int age;
}

✔ Clean

✔ Self-documenting

✔ Interview-ready

---

### 7️⃣ What Happens Internally (VERY IMPORTANT)
1. Request hits controller
2. Spring calls Hibernate Validator
3. Each field constraint is checked
4. If ANY fails:
      - MethodArgumentNotValidException thrown
5. Controller method is not executed

This is Aspect-oriented behavior (cross-cutting concern).

---

### 8️⃣ Handling Validation Errors (PRODUCTION LEVEL)
#### ❌ Default Error (Ugly)

Spring returns a big JSON with internal details.

#### ✅ Custom Global Exception Handler

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
          .forEach(error ->
              errors.put(error.getField(), error.getDefaultMessage())
          );

        return ResponseEntity.badRequest().body(errors);
    }
}

📌 Output:

{

  "name": "Name is mandatory",
  
  "mobile": "Mobile must be 10 digits"
  
}

🔥 Recruiter-level API response.

---

### 9️⃣ Custom Validator (ADVANCED & INTERVIEW FAVORITE)
#### When Built-in Is Not Enough?

Examples:
- Username must not contain admin keywords
- Password rules (upper + lower + digit)
- Custom business logic

#### Step 1: Create Annotation

@Target({ FIELD })

@Retention(RUNTIME)

@Constraint(validatedBy = PasswordValidator.class)

public @interface StrongPassword {

    String message() default "Weak password";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


#### Step 2: Create Validator Logic

public class PasswordValidator

        implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        return value.matches(
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$"
        );
    }
}

#### Step 3: Use in DTO

@StrongPassword

private String password;

✔ Clean

✔ Reusable

✔ Enterprise-grade

---

### 🔟 @Validated vs @Valid (INTERVIEW QUESTION)

| `@Valid`            | `@Validated`          |
| ------------------- | --------------------- |
| Standard Java       | Spring-specific       |
| No groups           | Supports groups       |
| Used in controllers | Used in service layer |

Example:

@Validated

public class UserService { }

---

### 1️⃣1️⃣ Common Mistakes (AVOID)

❌ Validating Entity

❌ No global exception handler

❌ Writing logic validation inside controller

❌ Ignoring error messages

❌ Using @NotNull for Strings instead of @NotBlank


---

### 1️⃣2️⃣ How Interviewers Judge You on Validation

They expect you to say:
> “We validate request DTOs using @Valid, handle errors globally with @ControllerAdvice, and create custom validators for business rules.”

Say this → strong signal 💪

---

# Spring Boot Validation – Interview Questions & Answers

## 1. What is validation in Spring Boot?
Validation is the process of verifying incoming request data to ensure it meets defined rules before business logic executes. It prevents invalid or malicious data from entering the system.

---

## 2. Why is validation important in backend applications?
- Prevents invalid data from reaching service and database layers  
- Improves application stability  
- Provides meaningful error responses to clients  
- Enforces business rules early (fail-fast principle)

---

## 3. What is `@Valid` in Spring Boot?
`@Valid` is a Java standard annotation used to trigger validation on an object based on constraints defined on its fields. It is commonly used in controller methods to validate request DTOs.

---

## 4. Where is `@Valid` usually applied?
`@Valid` is typically applied:
- On `@RequestBody`
- On `@PathVariable`
- On `@RequestParam`

Example:
```java
@PostMapping("/users")
public ResponseEntity<?> create(@Valid @RequestBody UserDTO dto) { }


5. What happens if validation fails?
If validation fails:

Spring throws MethodArgumentNotValidException

Controller method is NOT executed

Error response is returned to the client



6. Why should validation be done on DTOs and not Entities?
DTOs represent API contracts

Entities represent database structure

Validation rules differ between API and database

Avoids polluting persistence layer with API concerns



7. What are common built-in validation annotations?
String validations:
@NotNull

@NotBlank

@NotEmpty

@Size

@Email

@Pattern

Number validations:
@Min

@Max

@Positive

@PositiveOrZero

@Negative

Date validations:
@Past

@Future

@FutureOrPresent



8. Difference between @NotNull, @NotEmpty, and @NotBlank?

| Annotation  | Description                          |
| ----------- | ------------------------------------ |
| `@NotNull`  | Value must not be null               |
| `@NotEmpty` | Not null + not empty                 |
| `@NotBlank` | Not null + not empty + no whitespace |



9. What dependency is required for validation?
Spring Boot Starter Validation:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>



10. How do you customize validation error messages?
By using the message attribute:

@NotBlank(message = "Name is mandatory")
private String name;


11. How do you handle validation errors globally?
Using @RestControllerAdvice and @ExceptionHandler for MethodArgumentNotValidException.


12. What is MethodArgumentNotValidException?
It is an exception thrown by Spring when request body validation fails due to constraint violations.


13. What is a custom validator?
A custom validator is used when built-in annotations are insufficient. It allows validation logic based on business rules.


14. Steps to create a custom validator?
Create a custom annotation

Implement ConstraintValidator

Define validation logic

Apply annotation on DTO field


15. What is ConstraintValidator?
It is an interface used to define custom validation logic for a specific annotation and data type.


16. Can custom validators be reused?
Yes. Custom validators are reusable across multiple DTOs and projects.


17. What is @Validated?
@Validated is a Spring-specific annotation that supports validation groups and is commonly used at the service layer.


18. Difference between @Valid and @Validated?
Feature	@Valid	@Validated
Standard	Java	Spring
Validation groups	❌ No	✅ Yes
Usage	Controller	Service / Controller


19. Can validation be applied at the service layer?
Yes, using @Validated on the service class and constraints on method parameters.


20. What are validation groups?
Validation groups allow applying different validation rules for different scenarios such as create vs update operations.


21. Common mistakes in validation?
Validating entities instead of DTOs

No global exception handler

Writing validation logic in controllers

Ignoring error messages

Using @NotNull instead of @NotBlank for strings


22. How does validation improve security?
Prevents malformed input

Reduces risk of injection attacks

Stops invalid data before business logic execution



23. How does Spring perform validation internally?
Spring delegates validation to Hibernate Validator, which checks constraints before method execution.



24. Is validation synchronous or asynchronous?
Validation is synchronous and happens before controller method execution.



25. How do you explain validation in an interview (best answer)?
“We validate request DTOs using @Valid, handle errors globally using @ControllerAdvice, and create custom validators for business-specific rules.”


---








