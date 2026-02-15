# ✅ Entity Mapping

# ✅ JPA Relationships

# ✅ Real Database Thinking

---

### 🔹 1️⃣ What is Entity Mapping?

In Spring Boot using Spring Data JPA, an Entity is a Java class mapped to a database table

👉 Think like this:

| Java   | Database |
| ------ | -------- |
| Class  | Table    |
| Object | Row      |
| Field  | Column   |

Basic Entity Example

@Entity

@Table(name = "students")

public class Student {

    @Id
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @Column(nullable = false)
    
    private String name;

    private String email;
    
}

🔎 Important Annotations

| Annotation        | Meaning                 |
| ----------------- | ----------------------- |
| `@Entity`         | Marks class as DB table |
| `@Table`          | Customize table name    |
| `@Id`             | Primary key             |
| `@GeneratedValue` | Auto increment          |
| `@Column`         | Customize column        |

---

### 🔹 2️⃣ Types of Relationships (Very Important)

Real-world databases are never single-table.

Example:
- One User → Many Orders
- One Order → Many Products
- One Student → One Address

JPA gives 4 main relationship types:
1. One-to-One
2. One-to-Many
3. Many-to-One
4. Many-to-Many

Let’s go deep 👇

---

### 🔵 1️⃣ One-to-One Relationship

Example:

One Student → One Address

Database Structure

Student Table

Address Table (student_id as foreign key)

Code Example

@Entity

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}

@Entity

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
}

Important:
- @JoinColumn → defines foreign key
- Default fetch type = EAGER

---

### 🔵 2️⃣ One-to-Many Relationship (Most Common)

Example:

One User → Many Orders

Database Structure

User Table

Order Table (user_id as foreign key)

Code

@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
}

@Entity

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

### 🔥 Very Important Concept

👉 mappedBy means:
> "I am not the owner of the relationship"

Owner = side having @JoinColumn

---

### 🔵 3️⃣ Many-to-One (Most Used in Real Projects)

Many Orders → One User

This is usually the owning side.

@ManyToOne

@JoinColumn(name = "user_id")

private User user;

Default fetch type = EAGER (important for performance discussion in interviews)

---

### 🔵 4️⃣ Many-to-Many Relationship

Example:

Students ↔ Courses

One student can enroll in many courses.

One course can have many students.

Database Structure

Student

Course

Student_Course (Join Table)

Code

@Entity

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}

@Entity

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
}

---

### 🔹 3️⃣ Fetch Types (Interview Favorite)

| Fetch Type | Meaning               |
| ---------- | --------------------- |
| EAGER      | Load immediately      |
| LAZY       | Load only when needed |

Example:

@OneToMany(fetch = FetchType.LAZY)

#### 🚨 Important:
- @OneToMany → LAZY (default)
- @ManyToOne → EAGER (default)

🔥 In real projects, we prefer LAZY to avoid performance issues.

---

### 🔹 4️⃣ Cascade Types

Cascade means:
> When parent operation happens, child also affected.

cascade = CascadeType.ALL

Types:
- PERSIST
- REMOVE
- MERGE
- ALL

Example:

If User is deleted → Orders also deleted.

---

### 🔹 5️⃣ Owning vs Inverse Side (Very Important)

Only owning side controls foreign key.

Owning side:

@JoinColumn

Inverse side:

mappedBy = "fieldName"

Interview question:

👉 “Why do we need mappedBy?”

Answer:

To avoid duplicate foreign key creation.

---

### 🔹 6️⃣ Real-World Example (E-commerce Thinking)

Imagine:

User

Order

OrderItem

Product

Relationships:

User → OneToMany → Order

Order → OneToMany → OrderItem

OrderItem → ManyToOne → Product

This is how real systems are designed.

---

### 🔹 7️⃣ Common Mistakes (Very Important)
1. Forgetting mappedBy
2. Using EAGER everywhere
3. Not handling bidirectional JSON (causes infinite loop)
4. Not using @JsonIgnore or @JsonManagedReference

---

### 🔹 8️⃣ Advanced Concepts (For 20 LPA Level 🔥)
- Bidirectional vs Unidirectional mapping
- Orphan Removal
- Composite Keys
- DTO pattern instead of exposing entities
- N+1 Query problem

---

### 🎯 Interview Questions for You
1. Difference between OneToMany and ManyToOne?
2. What is owning side?
3. Why LAZY loading preferred?
4. What is cascade?
5. What is N+1 problem?

---

### 🚀 Day 43 Summary

Today you learned:

✔ Entity mapping

✔ All relationship types

✔ Fetch types

✔ Cascade types

✔ Owning side logic

✔ Real DB thinking

---

# Spring Boot – Entity Mapping & Relationships
## Interview Questions & Answers

---

## 1. What is an Entity in Spring Boot?

An Entity is a Java class mapped to a database table using JPA annotations.

- `@Entity` → Marks class as a database table
- `@Table` → Customizes table name
- `@Id` → Primary key
- `@GeneratedValue` → Auto-increment strategy

Entity represents a table.
Object represents a row.

---

## 2. What are the different types of JPA relationships?

There are 4 types:

1. One-to-One
2. One-to-Many
3. Many-to-One
4. Many-to-Many

These define how tables are related in relational databases.

---

## 3. What is the difference between OneToMany and ManyToOne?

### OneToMany
- One parent has multiple children.
- Example: One User → Many Orders
- Default FetchType = LAZY

### ManyToOne
- Many children belong to one parent.
- Example: Many Orders → One User
- Default FetchType = EAGER

In real projects, ManyToOne is more commonly used as the owning side.

---

## 4. What is the owning side in JPA?

The owning side is the side that contains the `@JoinColumn` annotation.

It controls the foreign key in the database.

Example:

```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
The inverse side uses mappedBy.

5. What is mappedBy in JPA?
mappedBy is used on the inverse side of a bidirectional relationship.

It tells JPA:
"I am not the owner of the relationship."

It prevents duplicate foreign key creation.

Example:

@OneToMany(mappedBy = "user")
private List<Order> orders;
6. What is FetchType in JPA?
FetchType defines when related data is loaded.

Two types:

EAGER → Loads immediately

LAZY → Loads only when accessed

Defaults:

OneToMany → LAZY

ManyToOne → EAGER

In real-world applications, LAZY is preferred for performance.

7. Why is LAZY loading preferred?
Because EAGER loading can:

Load unnecessary data

Reduce performance

Cause memory issues

LAZY improves scalability and performance.

8. What is Cascade in JPA?
Cascade defines what happens to child entities when parent is affected.

Common types:

PERSIST

MERGE

REMOVE

ALL

Example:
If User is deleted and cascade = REMOVE,
All related Orders are also deleted.

9. What is the N+1 Query Problem?
N+1 occurs when:

1 query loads parent records
Then N additional queries load child records

Example:
Fetching 100 users → 1 query
Fetching each user's orders → 100 extra queries

Total = 101 queries

This causes performance issues.

Solution:

Use JOIN FETCH

Use EntityGraph

Optimize queries

10. What is a Join Table in ManyToMany?
In ManyToMany relationships, a third table is created.

Example:

Student
Course
Student_Course (Join Table)

It contains:

student_id

course_id

Defined using @JoinTable.

11. What is the difference between Unidirectional and Bidirectional mapping?
Unidirectional
Only one entity knows about the relationship.

Bidirectional
Both entities reference each other.

Bidirectional requires:

mappedBy

Proper JSON handling to avoid infinite recursion

12. What is orphanRemoval?
orphanRemoval = true

If a child is removed from parent collection,
It will also be deleted from database.

Example:

@OneToMany(mappedBy = "user", orphanRemoval = true)
13. Why should we not expose Entity directly in REST APIs?
Because:

It tightly couples DB structure with API

Can expose sensitive fields

Can cause infinite recursion in bidirectional mapping

Best practice:
Use DTO (Data Transfer Object).

14. What are common mistakes in JPA relationships?
Forgetting mappedBy

Using EAGER everywhere

Not handling JSON infinite loop

Not understanding owning side

Ignoring N+1 problem

15. Real-World Example of Relationships
E-commerce system:

User → OneToMany → Order
Order → OneToMany → OrderItem
OrderItem → ManyToOne → Product

This is how scalable systems are designed.

Summary
Entity mapping converts Java classes into database tables.

Understanding:

Relationship types

Fetch strategy

Cascade

Owning side

Performance issues

is critical for backend interviews and real-world projects.

