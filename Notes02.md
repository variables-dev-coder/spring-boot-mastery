# 📘 Spring Boot Day 29  
## Project Structure & application.properties vs application.yml

---

### 1️⃣ Spring Boot Project Structure (Expert Level)

Spring Boot enforces a **convention-based project structure** to promote:
- Clean architecture
- Separation of concerns
- Easy maintainability
- Predictable component scanning

### Standard Maven Structure

project-root

├── src

│ ├── main

│ │ ├── java

│ │ │ └── com/munna/springboot

│ │ │ ├── Day29Application.java

│ │ │ ├── controller

│ │ │ ├── service

│ │ │ ├── repository

│ │ │ ├── entity

│ │ │ ├── dto

│ │ │ ├── exception

│ │ │ └── config

│ │ └── resources

│ │ ├── application.properties | application.yml

│ │ ├── static

│ │ └── templates

│ └── test

└── pom.xml


---

### 🔑 Responsibility of Each Layer

| Layer | Purpose |
|-----|--------|
| controller | Handles HTTP requests & responses |
| service | Business logic |
| repository | Database interaction |
| entity | Database table mapping |
| dto | API request/response models |
| exception | Centralized error handling |
| config | Security, CORS, custom beans |

📌 **Design Principle**  
> One class → one responsibility

---

### 🚨 Main Class Placement Rule (VERY IMPORTANT)

```java
@SpringBootApplication
public class Day29Application { }


✔ Must be placed in the root package

Why?

- Spring Boot scans only sub-packages
- Incorrect placement leads to missing beans

❌ Wrong:

com.munna.controller
com.munna.service
com.munna.main (main class here)


✅ Correct:

com.munna

 ├── controller

 ├── service

 └── Day29Application

### 2️⃣ application.properties vs application.yml

These files provide externalized configuration.

Meaning:
- No hardcoding
- Environment-specific configs
- Change behavior without recompiling

application.properties

server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/testdb

spring.datasource.username=root

spring.datasource.password=1234

logging.level.root=INFO

Pros
- Simple
- Less syntax errors
- Beginner-friendly

Cons
- Repetitive keys
- Poor readability for large configs

application.yml (YAML)

server:

  port: 8081

spring:

  datasource:

    url: jdbc:mysql://localhost:3306/testdb

    username: root

    password: 1234

logging:

  level:

    root: INFO


Pros
- Clean & hierarchical
- Ideal for large projects
- Easier to maintain

Cons
- Indentation-sensitive
- Small spacing error can break startup


⚙️ Internal Truth (Interview Gold)

Spring Boot internally converts YAML to properties.

➡ No performance difference

➡ Choice is about readability & scale

### 3️⃣ Comparison Summary

| Aspect      | properties | yml             |
| ----------- | ---------- | --------------- |
| Readability | Medium     | High            |
| Hierarchy   | Manual     | Natural         |
| Error risk  | Low        | Indentation     |
| Best use    | Small apps | Enterprise apps |

📌 Interview answer:
> “I use properties for small apps and YAML for complex configurations due to better structure.”


### 4️⃣ Configuration Loading Order (Advanced)

Priority (High → Low):
1. Command-line arguments
2. Environment variables
3. application-{profile}.yml
4. application.yml
5. application.properties
➡ Higher priority overrides lower.

### 5️⃣ Is a Project Mandatory for Day 29?

❌ Full project → NOT mandatory

✅ Mini config demo → Recommended

Reason:
- Day 29 focuses on architecture & configuration
- No business logic needed


## 6️⃣ Interview Questions & Answers (Day 29)

Q1. Why must the main class be in the root package?

Answer:

Spring Boot scans only sub-packages from the main class location. If placed incorrectly, components won’t be detected.

---

Q2. Difference between application.properties and application.yml?

Answer:

Both serve the same purpose. YAML provides hierarchical and readable structure, while properties are simpler and flat.

---

Q3. Is there any performance difference between properties and YAML?

Answer:

No. Spring Boot internally converts YAML into properties.


---

Q4. Can we use both properties and YAML together?

Answer:

Yes, but it’s discouraged. If both exist, properties take precedence.

---

Q5. What is externalized configuration?

Answer:

Configuration that is kept outside code so application behavior can change without recompilation.

---

Q6. How does Spring Boot decide which config to load?

Answer:

It follows a predefined precedence order: command-line args → env variables → profile configs → default configs.

---

Q7. Why is clean project structure important?

Answer:

It improves maintainability, scalability, testing, and makes the project understandable for teams.

---

Q8. Can we override values defined in application.yml?

Answer:

Yes, using environment variables, command-line args, or profile-specific files.

---

🎯 Day 29 Key Takeaway
> Spring Boot project structure enforces clean architecture, while application.properties or YAML enables externalized, environment-driven configuration without changing code.








