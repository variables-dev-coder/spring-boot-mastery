# 🌱 Spring Boot Day 34
## Custom Banner & Startup Hooks (CommandLineRunner)

## PART 1️⃣ Custom Banner (Branding + Control)

### 1️⃣ What is a Spring Boot Banner?

When a Spring Boot app starts, you see this:

  ____              _         ____              _
 / ___| _ __  _ __ (_)_ __   | __ )  ___   ___ | |_
 \___ \| '_ \| '_ \| | '_ \  |  _ \ / _ \ / _ \| __|
  ___) | |_) | |_) | | | | | | |_) | (_) | (_) | |_
 |____/| .__/| .__/|_|_| |_| |____/ \___/ \___/ \__|
       |_|   |_|

📌 This is the Spring Boot banner.

---

### 2️⃣ Why Custom Banner Exists (REAL REASONS)
- Branding (company / project identity)
- Environment identification (DEV / PROD)
- Security (hide framework version)
- Professional startup logs

📌 In real companies, default banner is often disabled or replaced.


---

### 3️⃣ How to Create a Custom Banner
#### Step 1️⃣ Create a file

src/main/resources/banner.txt

#### Step 2️⃣ Add custom text

Example:

=============================

  SPRING BOOT PROFILES DEMO
  
  Environment Ready 🚀
  
=============================


#### ➡ Spring Boot automatically detects banner.txt.

No configuration needed.

---

### 4️⃣ Advanced Banner Options

Disable banner completely

spring:

  main:
  
    banner-mode: off

Banner only in console (default)

spring:

  main:
  
    banner-mode: console

Banner from custom location

spring:

  banner:
  
    location: classpath:custom-banner.txt

---

### 5️⃣ Interview Insight (Banner)

> Custom banners are used for branding and environment awareness during application startup and can be disabled for cleaner logs in production.

## PART 2️⃣ Startup Hooks – CommandLineRunner

### 6️⃣ What is a Startup Hook?

A startup hook is code that runs:
> AFTER Spring Boot application context is fully initialized

📌 Meaning:
- All beans created
- DI completed
- Server started (for web apps)

---

### 7️⃣ What is CommandLineRunner?

public interface CommandLineRunner {

    void run(String... args) throws Exception;
    
}


Any bean implementing this interface:

➡ Executes once at application startup

---

### 8️⃣ Basic Example

@Component

public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Application started successfully");
    }
}

📌 Runs after context load, before app is ready for requests.


---

### 9️⃣ Real-World Use Cases

CommandLineRunner is used for:
- Database seed data
- Cache preloading
- Validating configuration
- Startup sanity checks
- Logging environment info
- One-time migration tasks

---

### 🔥 Example: Print Active Profile on Startup
@Component

public class ProfileLogger implements CommandLineRunner {

    @Value("${spring.profiles.active:default}")
    private String profile;

    @Override
    public void run(String... args) {
        System.out.println("Active Profile: " + profile);
    }
}

📌 Very common in production apps.

---

### 🔟 Multiple CommandLineRunners (IMPORTANT)

You can have multiple runners.

#### Control order using @Order

@Component

@Order(1)

public class FirstRunner implements CommandLineRunner {

    public void run(String... args) {
    
        System.out.println("First");
    }
    
}

@Component

@Order(2)

public class SecondRunner implements CommandLineRunner {

    public void run(String... args) {
    
        System.out.println("Second");
        
    }
    
}

📌 Execution order matters in real systems.

---

### 1️⃣1️⃣ Difference: CommandLineRunner vs ApplicationRunner

| Aspect     | CommandLineRunner | ApplicationRunner    |
| ---------- | ----------------- | -------------------- |
| Args type  | String[]          | ApplicationArguments |
| Complexity | Simple            | Structured           |
| Usage      | Common            | Advanced             |

📌 Most projects use CommandLineRunner.

---

### 1️⃣2️⃣ Common Mistakes (REAL WORLD)

❌ Heavy logic in startup

❌ Long-running tasks blocking startup

❌ Using startup hooks for request logic

❌ Forgetting order when multiple runners exist

📌 Startup hooks should be fast & safe.

---

### 1️⃣3️⃣ Interview Questions (Preview)
- When does CommandLineRunner run?
- Difference between ApplicationRunner & CommandLineRunner?
- Can multiple runners exist?
- How to control execution order?
- Should we use startup hooks in production?

(All answered above)

---

## 🎯 Day 34 Final Takeaway (MEMORIZE)
> Custom banners help brand and control application startup visibility, while CommandLineRunner provides a safe hook to execute logic after Spring Boot initialization.

---

## Custom Banner & Startup Hooks (CommandLineRunner)

---

## Q1. What is the Spring Boot banner?
The Spring Boot banner is the ASCII text displayed in the console when a Spring Boot application starts.

---

## Q2. Why would you customize or disable the Spring Boot banner?
To apply branding, identify environments, reduce log noise, or hide framework details in production.

---

## Q3. How do you create a custom banner in Spring Boot?
By adding a `banner.txt` file in `src/main/resources`. Spring Boot automatically loads it at startup.

---

## Q4. How do you disable the Spring Boot banner?

```yaml
spring:
  main:
    banner-mode: off

Q5. Can the banner be loaded from a custom location?
Yes.


spring:
  banner:
    location: classpath:custom-banner.txt

Q6. When is the Spring Boot banner printed?
Before the application context is fully initialized, during the early startup phase.

Q7. What is CommandLineRunner?
CommandLineRunner is a Spring Boot interface used to run code once after the application context is fully initialized.

Q8. When does CommandLineRunner execute?
After all beans are created and dependency injection is complete, but before the application starts serving requests.

Q9. How do you implement CommandLineRunner?

@Component
public class StartupRunner implements CommandLineRunner {
    public void run(String... args) {
        // startup logic
    }
}


Q10. What are common real-world use cases of CommandLineRunner?
Database seeding

Cache warm-up

Configuration validation

Startup logging

One-time initialization tasks


Q11. Can multiple CommandLineRunner beans exist?
Yes. Spring Boot executes all CommandLineRunner beans.

Q12. How do you control the execution order of multiple CommandLineRunners?
Using @Order.


@Order(1)
@Component
class FirstRunner implements CommandLineRunner { }


Q13. What happens if no @Order is specified?
Spring executes runners in an undefined order.

Q14. Difference between CommandLineRunner and ApplicationRunner?
| Aspect     | CommandLineRunner | ApplicationRunner    |
| ---------- | ----------------- | -------------------- |
| Arguments  | String[]          | ApplicationArguments |
| Complexity | Simple            | Structured           |
| Usage      | More common       | Less common          |


Q15. Which one is preferred in most projects?
CommandLineRunner, because it is simpler and sufficient for most startup tasks.

Q16. Should heavy logic be placed inside CommandLineRunner?
No. Heavy or long-running logic can delay startup and cause failures.

Q17. Does CommandLineRunner run for web applications?
Yes. It runs for both web and non-web Spring Boot applications.

Q18. Can CommandLineRunner access Spring beans?
Yes. It is a Spring-managed bean and fully supports dependency injection.

Q19. Is CommandLineRunner executed in tests?
Yes, unless the test context configuration disables it or uses profiles that exclude it.

Q20. One-line interview answer (MEMORIZE)
Custom banners control application startup visibility, while CommandLineRunner allows execution of safe, one-time logic after Spring Boot initialization.


