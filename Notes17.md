# 🔥 1️⃣ Repository Pattern – Expert Level Understanding

### 🧠 What Problem Does It Solve?

#### Without repository pattern:
- Controller talks directly to DB logic
- SQL scattered everywhere
- Tight coupling
- Hard to test
- Hard to replace DB (MySQL → MongoDB)

Repository pattern introduces:
> 🟢 An abstraction layer between domain/business logic and data access logic.

It separates:
- What to fetch
- From
- How to fetch

That’s architectural maturity.


---


### 🏗 Clean Architecture View

Controller → Service → Repository → Database

- Controller = HTTP layer
- Service = Business logic
- Repository = Data access abstraction
- Database = Storage

This keeps your application:

✔ Maintainable

✔ Testable

✔ Replaceable

✔ Scalable

---

### 🔥 2️⃣ How Spring Boot Implements Repository Pattern

#### Spring Boot uses:

Spring Data JPA

#### Core interface:

JpaRepository<T, ID>

#### Example:

public interface EmployeeRepository 

        extends JpaRepository<Employee, Long> {
}

#### Spring automatically provides:
- save()
- findById()
- findAll()
- delete()
- count()
- existsById()

No implementation needed.

That’s powerful abstraction.


---

### 🔥 3️⃣ Types of Repository Interfaces
#### 1️⃣ CrudRepository

Basic CRUD

#### 2️⃣ PagingAndSortingRepository

Adds:
- Pagination
- Sorting

#### 3️⃣ JpaRepository (Most Used)

Adds:
- Batch operations
- Flush
- More JPA-specific features

In real projects → always use JpaRepository.

---

### 🔥 4️⃣ Query Methods (Magic of Spring Data)

Now we go expert mode.

Spring parses method names and generates SQL automatically.

Example:

List<Employee> findByName(String name);

Spring converts it internally to:

SELECT * FROM employee WHERE name = ?

No SQL written.

This is called Derived Query Methods.

---

### 🔥 5️⃣ Advanced Query Method Keywords

Spring understands keywords like:

#### Comparison
- findByAgeGreaterThan
- findBySalaryLessThan
- findByAgeBetween

#### String
- findByNameContaining
- findByNameStartingWith
- findByNameEndingWith
- findByNameIgnoreCase

#### Boolean
- findByActiveTrue
- findByActiveFalse

#### Null
- findByDepartmentIsNull
- findByDepartmentIsNotNull

#### AND / OR

findByNameAndDepartment

findByNameOrDepartment

---

### 🔥 6️⃣ Sorting + Pagination in Query Methods

Page<Employee> findByDepartment(String dept, Pageable pageable);

Used like:

PageRequest.of(0, 10, Sort.by("salary").descending())

This is critical for:
- Large datasets
- Production APIs
- Performance optimization

Senior engineers ALWAYS paginate

---

### 🔥 7️⃣ Custom Queries (@Query Annotation)

#### When method name becomes too complex:

@Query("SELECT e FROM Employee e WHERE e.salary > :salary")

List<Employee> findHighSalaryEmployees(@Param("salary") double salary);

JPQL (Object oriented query)

OR Native SQL:

@Query(value = "SELECT * FROM employee WHERE salary > ?1", nativeQuery = true)

List<Employee> findHighSalaryEmployees(double salary);


---


### 🔥 8️⃣ Performance Considerations (Expert Level)
#### ❌ N+1 Problem

If you fetch parent and lazy child inside loop → multiple queries.

Solution:

@Query("SELECT e FROM Employee e JOIN FETCH e.department")

Or use:
- @EntityGraph

#### ❌ Returning List for Large Data

Bad:

List<Employee> findAll();

Better:

Page<Employee> findAll(Pageable pageable);

#### ❌ Fetching Unnecessary Columns

Use Projection:

public interface EmployeeNameView {

    String getName();
}

List<EmployeeNameView> findByDepartment(String dept);

Only required columns fetched.

That’s optimization mindset.


---

### 🔥 9️⃣ Transaction Awareness

Repository layer is transactional by default (read-only).

For write-heavy operations:

Use:

@Transactional

At service layer, not repository.

Clean design principle:
> Transactions belong to service layer.


---


### 🔥 1️⃣0️⃣ Real Interview Questions

You should be able to answer:
1. What is repository pattern?
2. Difference between CrudRepository and JpaRepository?
3. What are derived query methods?
4. How does Spring generate SQL from method names?
5. How to avoid N+1 problem?
6. How to optimize large dataset fetch?
7. What is projection?
8. Difference between JPQL and Native query?
9. Where should @Transactional be used?
10. When to use @Query instead of derived method?


---

### 🧠 Senior-Level Insight

Repository pattern is not just CRUD.

It is about:
- Domain abstraction
- Clean architecture
- Performance design
- Data consistency
- Scalability thinking

If you master this deeply, your backend skill jumps 2 levels.

---


# 🎯 Interview Questions & Answers (Expert Level)

### 1️⃣ What is the Repository Pattern?

Answer:

The Repository Pattern is a design pattern that abstracts data access logic from business logic.

It acts as a mediator between:
- Domain/Service layer
- Data source (Database)

It provides:
- Clean separation of concerns
- Loose coupling
- Easier testing
- Better maintainability

In Spring Boot, it is implemented using Spring Data JPA interfaces like JpaRepository.

---

### 2️⃣ Why do we need Repository Pattern in Spring Boot?

Answer:

Without Repository Pattern:
- SQL queries spread across the application
- Tight coupling between service and database
- Hard to test and maintain

With Repository Pattern:
- Data access logic is centralized
- Application becomes database-agnostic
- Easy to switch DB (e.g., MySQL → PostgreSQL)

Supports clean architecture

---

### 3️⃣ What is JpaRepository?

Answer:

JpaRepository<T, ID> is a Spring Data interface that provides:
- CRUD operations
- Pagination
- Sorting
- Batch operations
- JPA-specific methods like flush()

It extends:
- PagingAndSortingRepository
- CrudRepository

In real projects, we mostly use JpaRepository.


---

### 4️⃣ Difference between CrudRepository, PagingAndSortingRepository, and JpaRepository?

| Feature              | CrudRepository | PagingAndSortingRepository | JpaRepository |
| -------------------- | -------------- | -------------------------- | ------------- |
| Basic CRUD           | ✅              | ✅                          | ✅             |
| Pagination           | ❌              | ✅                          | ✅             |
| Sorting              | ❌              | ✅                          | ✅             |
| Batch operations     | ❌              | ❌                          | ✅             |
| JPA-specific methods | ❌              | ❌                          | ✅             |

---

### 5️⃣ What are Derived Query Methods?

Answer:

Derived Query Methods are methods where Spring automatically generates SQL queries based on method names.

Example:

List<Employee> findByName(String name);

Spring internally converts it to:

SELECT * FROM employee WHERE name = ?

No SQL writing required.

---

### 6️⃣ How does Spring generate SQL from method names?

Answer:

Spring Data JPA:
1. Parses method name.
2. Identifies entity field names.
3. Matches keywords like:
    - And
    - Or
    - Between
    - Like
4. GreaterThan
5. Generates JPQL query.
6. Converts JPQL to SQL using Hibernate.


---

### 7️⃣ When should we use @Query instead of derived methods?

Answer:

Use @Query when:
- Method name becomes too long
- Complex joins are required
- Custom JPQL needed
- Native SQL needed
- Performance optimization required


---

### 8️⃣ Difference between JPQL and Native Query?

| Feature        | JPQL            | Native Query    |
| -------------- | --------------- | --------------- |
| Works with     | Entity objects  | Database tables |
| DB independent | ✅               | ❌               |
| Syntax         | Object-oriented | SQL             |
| Portable       | ✅               | ❌               |


JPQL is preferred unless DB-specific optimization is required.

---

### 9️⃣ What is Pagination in Spring Data?

Answer:

Pagination limits the number of records fetched from the database.

Used via:

Page<Employee> findByDepartment(String dept, Pageable pageable);

Benefits:
- Improves performance
- Reduces memory usage
- Essential for large datasets

---

### 🔟 What is the N+1 Problem?

Answer:

The N+1 problem occurs when:

1 query fetches parent entities

Then N additional queries fetch child entities.

Example:
- Fetch 10 employees
- For each employee, fetch department
  
→ 11 queries total

Solution:
- JOIN FETCH
- @EntityGraph
- Change fetch type carefully


---

### 1️⃣1️⃣ What is Projection in Spring Data JPA?

Answer:

Projection fetches only required columns instead of entire entity.

Example:

public interface EmployeeNameView {

    String getName();
}

List<EmployeeNameView> findByDepartment(String dept);

Benefits:
- Reduces memory usage
- Improves performance
- Cleaner API responses

---

### 1️⃣2️⃣ Where should @Transactional be used?

Answer:

@Transactional should be used in the Service layer, not in the Repository layer.

Reason:
- Business logic controls transaction boundaries
- Keeps architecture clean
- Supports atomic operations

---

### 1️⃣3️⃣ What happens internally when we call save()?

Answer:

Internally:

1. Entity is managed by Hibernate.
2. If ID is null → INSERT.
3. If ID exists → UPDATE.
4. SQL executed during flush or transaction commit.

---

### 1️⃣4️⃣ What is the difference between findById() and getById()?

| Method     | Behavior                           |
| ---------- | ---------------------------------- |
| findById() | Returns Optional immediately       |
| getById()  | Returns proxy, fetches data lazily |

---

### 1️⃣5️⃣ What are performance best practices in Repository Layer?

Answer:
1. Always use Pagination for large data
2. Avoid unnecessary findAll()
3. Use Projection when possible
4. Avoid N+1 problem
5. Use proper indexing in DB
6. Avoid fetching unnecessary relationships

---

### 🧠 Final Interview Insight

A junior developer knows:
- How to use JpaRepository

A senior developer knows:
- How queries are generated
- When to use projection
- How to fix N+1
- How to optimize performance
- Where to manage transactions


---

