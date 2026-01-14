# 🌱 Spring Boot Day 32

## Dependency Injection, @Autowired, Constructor Injection (Deep Dive)
---

### 1️⃣ What is Dependency Injection? (CORE IDEA)

Simple Definition
> Dependency Injection (DI) means:
>
> An object does NOT create its own dependencies — Spring provides them.

#### ❌ Without Dependency Injection (Bad Design)

public class OrderService {

    private OrderRepository repo = new OrderRepository();
    
}

Problems:
- Tight coupling
- Hard to test
- No flexibility
- Not manageable by Spring

#### ✅ With Dependency Injection (Spring Way)


@Service

public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }
}

✔ Loose coupling

✔ Easy testing

✔ Spring-managed

✔ Clean design

📌 This is Dependency Injection.

---

### 2️⃣ Why Dependency Injection Exists (INTERVIEW GOLD)

DI solves:
- Tight coupling
- Object creation responsibility
- Testing difficulty
- Poor scalability

📌 Interview line:
> Dependency Injection allows Spring to control object creation and wiring, promoting loose coupling and testability.

---

### 3️⃣ Relationship Between IoC and DI (IMPORTANT)

| Concept                    | Meaning                         |
| -------------------------- | ------------------------------- |
| IoC (Inversion of Control) | Spring controls object creation |
| DI (Dependency Injection)  | Spring injects dependencies     |

📌 DI is how IoC is implemented.

---

### 4️⃣ How Spring Injects Dependencies (Behind the Scenes)

Spring:
- Scans beans
- Creates bean instances
- Identifies dependencies
- Injects dependencies
- Manages lifecycle

📌 Injection happens before @PostConstruct.

---

### 5️⃣ @Autowired – What It Really Does

What is @Autowired?
> Tells Spring: “Inject the required dependency here.”

@Autowired

private OrderRepository repo;


Spring looks for:
- A bean of matching type
- Exactly one candidate

---

### 6️⃣ Types of Dependency Injection in Spring

Spring supports 3 types:

1. Field Injection ❌
2. Setter Injection ⚠️
3. Constructor Injection ✅ (BEST)

---

### 7️⃣ Field Injection (NOT RECOMMENDED)

@Service

public class OrderService {

    @Autowired
    
    private OrderRepository repo;
    
}

#### ❌ Problems

- Hidden dependencies
- Hard to test
- Breaks immutability
- Not recommended by Spring team

📌 Use only for quick demos.

---

### 8️⃣ Setter Injection (LIMITED USE)

@Service

public class OrderService {

    private OrderRepository repo;

    @Autowired
    
    public void setRepo(OrderRepository repo) {
    
        this.repo = repo;
        
    }
    
}

#### When to use?
- Optional dependencies
- Rare cases

---

### 9️⃣ Constructor Injection (BEST PRACTICE ✅)

@Service

public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
    
        this.repo = repo;
        
    }
    
}

#### Why Constructor Injection is BEST

✔ Dependencies are mandatory

✔ Immutable objects

✔ Easy unit testing

✔ No reflection tricks

✔ Clear design

📌 Spring official recommendation.


---

### 🔥 Important Rule (VERY IMPORTANT)

Single Constructor → @Autowired NOT required

@Service

public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
    
        this.repo = repo;
        
    }
    
}


✔ Spring automatically injects

✔ Cleaner code

📌 Interview gold.

---

### 1️⃣0️⃣ Multiple Beans Problem (Real World)

@Repository

public class MySqlRepo implements OrderRepository { }

@Repository

public class MongoRepo implements OrderRepository { }

❓ Which one to inject?

#### Solution 1️⃣ @Primary

@Primary

@Repository

public class MySqlRepo implements OrderRepository { }


#### Solution 2️⃣ @Qualifier

@Service

public class OrderService {

    private final OrderRepository repo;

    public OrderService(@Qualifier("mongoRepo") OrderRepository repo) {
    
        this.repo = repo;
        
    }
    
}

📌 Prefer @Qualifier for clarity.

---

### 1️⃣1️⃣ Optional Dependencies

@Autowired(required = false)

private AuditService auditService;

📌 Rarely used in clean architecture.

---

### 1️⃣2️⃣ Common DI Mistakes (REAL WORLD)

❌ Using field injection everywhere

❌ Creating objects with new

❌ Ignoring constructor injection

❌ Multiple beans without qualifier

---

### 1️⃣3️⃣ Interview Questions (CRITICAL)

❓ Why constructor injection is preferred?

Because it ensures immutability, mandatory dependencies, and better testability.

❓ Difference between IoC and DI?

IoC is the principle, DI is the implementation.

❓ When is @Autowired optional?

When a class has only one constructor.

❓ What happens if no bean is found?

Spring throws NoSuchBeanDefinitionException.

---

### 🎯 Day 32 Final Takeaway (MEMORIZE)
> Dependency Injection allows Spring to manage object creation and wiring. Constructor injection is the best practice because it enforces immutability, clarity, and testability.

---






