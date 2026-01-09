# 📘 Spring Boot Day 31
## Bean Lifecycle & Stereotype Annotations
(@Component, @Service, @Repository)

---

# 1. Spring Bean – Fundamental Concept

## 1.1 What is a Spring Bean?
A **Spring Bean** is an object that is:
- Created
- Managed
- Injected
- Destroyed

by the **Spring IoC Container**.

📌 Only objects managed by the container are called Spring Beans.

```java
@Component
public class UserService { }
1.2 What is NOT a Spring Bean?
java
Copy code
UserService service = new UserService(); // NOT a Spring Bean
📌 Objects created using new are outside Spring control.

2. Spring IoC Container
2.1 What is IoC?
Inversion of Control means:

Object creation and lifecycle are controlled by Spring, not the developer.

2.2 Types of IoC Containers
Container	Description
BeanFactory	Basic, lazy initialization
ApplicationContext	Advanced, enterprise-level

📌 Spring Boot always uses ApplicationContext.

3. Spring Bean Lifecycle (CORE CONCEPT)
3.1 Complete Lifecycle Order (EXACT SEQUENCE)
Bean definition loaded

Bean instance created

Dependencies injected

Aware interfaces executed

BeanPostProcessor (before initialization)

Initialization logic executed

BeanPostProcessor (after initialization)

Bean ready for use

Application context shutdown

Destruction logic executed

📌 This lifecycle is fully managed by Spring.

4. Bean Lifecycle Hooks (Developer Control)
4.1 Initialization Phase
@PostConstruct
java
Copy code
@PostConstruct
public void init() {
    System.out.println("Initialization logic");
}
Runs once

After dependency injection

Before bean is used

4.2 Destruction Phase
@PreDestroy
java
Copy code
@PreDestroy
public void cleanup() {
    System.out.println("Cleanup logic");
}
Runs once

Before container shutdown

📌 Preferred lifecycle hooks in Spring Boot

5. Alternative Lifecycle APIs (DO NOT CONFUSE)
5.1 InitializingBean
java
Copy code
public class DemoBean implements InitializingBean {
    public void afterPropertiesSet() {
        // init logic
    }
}
5.2 DisposableBean
java
Copy code
public class DemoBean implements DisposableBean {
    public void destroy() {
        // cleanup logic
    }
}
📌 Less preferred than annotations.

6. BeanPostProcessor (ADVANCED – INTERNAL)
6.1 What is it?
A BeanPostProcessor allows Spring to:

Modify beans before initialization

Modify beans after initialization

6.2 Used Internally By
AOP

@Transactional

Dependency Injection

Proxy creation

📌 Rarely written by developers, commonly asked in interviews.

7. Stereotype Annotations – Concept
7.1 What are Stereotype Annotations?
They:

Mark a class as a Spring Bean

Define its role in architecture

Improve readability & maintenance

📌 All stereotypes are based on @Component.

8. @Component
8.1 Purpose
Generic bean

No specific layer meaning

java
Copy code
@Component
public class DateUtil { }
📌 Used for helpers and utilities.

9. @Service
9.1 Purpose
Represents business logic layer

java
Copy code
@Service
public class OrderService { }
📌 Semantically indicates service responsibility.

10. @Repository
10.1 Purpose
Represents data access layer

java
Copy code
@Repository
public class OrderRepository { }
10.2 Special Feature – Exception Translation
Automatically converts:

SQLException
→ DataAccessException (unchecked)

📌 ONLY @Repository provides this behavior.

11. Stereotype Comparison (INTERVIEW CRITICAL)
Annotation	Layer	Extra Capability
@Component	Generic	Base stereotype
@Service	Business	Semantic clarity
@Repository	Data	Exception translation

12. Component Scanning & Bean Creation Flow
text
Copy code
@ComponentScan
   ↓
Detect stereotype annotations
   ↓
Create bean definitions
   ↓
Execute bean lifecycle
📌 This is why correct package structure is mandatory.

13. Common Mistakes (REAL WORLD)
❌ Using new keyword
❌ Using @Component everywhere
❌ Ignoring lifecycle hooks
❌ Assuming Spring manages all objects

14. Best Practices (INDUSTRY)
Controller → @RestController

Business logic → @Service

Database layer → @Repository

Utility classes → @Component

Startup logic → @PostConstruct

Cleanup logic → @PreDestroy

15. Interview Questions & Answers
Q1. What is a Spring Bean?
An object managed by the Spring IoC container.

Q2. Who controls the bean lifecycle?
The Spring IoC container (ApplicationContext).

Q3. Difference between @Component and @Service?
Both create beans, but @Service represents business logic semantically.

Q4. Why is @Repository special?
It provides automatic persistence exception translation.

Q5. When does @PostConstruct execute?
After dependency injection and before the bean is used.

Q6. When does @PreDestroy execute?
Before application context shutdown.

🎯 Final Takeaway
Spring manages the complete lifecycle of beans, and stereotype annotations clearly define responsibility, structure, and additional behavior like exception translation.
