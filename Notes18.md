# 🔥 Spring Boot Day 45 – JPQL & Native Queries (Expert but Simple)

#### Before this, you were using:

findByName(String name)

findByEmailContaining(String keyword)

That is Derived Query Methods.

Now we go one level deeper.

---

### 1️⃣ What is JPQL?
#### 👉 JPQL = Java Persistence Query Language

It looks like SQL…

BUT it works on Entity objects, not tables.

❌ SQL → Works on table & column names

✅ JPQL → Works on Entity class & field names

#### 🧠 Example Setup

##### Entity

@Entity

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private double salary;
}

Table name: employee

Columns: id, name, department, salary


---

### 2️⃣ JPQL Basic Query

#### 🔹 Find employees by department

@Query("SELECT e FROM Employee e WHERE e.department = :dept")

List<Employee> findByDepartment(@Param("dept") String department);


Important Points:
- Employee → Entity class name (NOT table name)
- e.department → field name (NOT column name)
- :dept → named parameter
- @Param("dept") → bind method parameter

---

### 3️⃣ JPQL with Multiple Conditions

@Query("SELECT e FROM Employee e WHERE e.department = :dept AND e.salary > :salary")

List<Employee> findByDeptAndSalary(@Param("dept") String dept, @Param("salary") double salary);

---

### 4️⃣ JPQL with LIKE (Search)

@Query("SELECT e FROM Employee e WHERE e.name LIKE %:keyword%")

List<Employee> searchByName(@Param("keyword") String keyword);

---

### 5️⃣ JPQL Sorting

@Query("SELECT e FROM Employee e ORDER BY e.salary DESC")

List<Employee> findAllSortedBySalary();

---

### 6️⃣ JPQL Aggregation (Expert Level 🔥)

@Query("SELECT AVG(e.salary) FROM Employee e")

Double getAverageSalary();

@Query("SELECT COUNT(e) FROM Employee e")

Long getEmployeeCount();

---

### 7️⃣ JPQL Projection (Advanced Concept)

#### Instead of returning full object:

@Query("SELECT e.name, e.salary FROM Employee e")

List<Object[]> getNameAndSalary();

Better approach:

@Query("SELECT new com.example.dto.EmployeeDTO(e.name, e.salary) FROM Employee e")

List<EmployeeDTO> getEmployeeData();

⚡ This is clean architecture practice.

---

### 8️⃣ JPQL Update & Delete (IMPORTANT ⚠️)

You must use:
- @Modifying
- @Transactional

@Modifying

@Transactional

@Query("UPDATE Employee e SET e.salary = :salary WHERE e.id = :id")

int updateSalary(@Param("id") Long id, @Param("salary") double salary);


Delete:

@Modifying

@Transactional

@Query("DELETE FROM Employee e WHERE e.department = :dept")

int deleteByDepartment(@Param("dept") String dept);

Return type → number of rows affected.

---

### 9️⃣ What is Native Query?

Native Query = Real SQL Query

You write database-specific SQL.

@Query(value = "SELECT * FROM employee WHERE department = :dept", nativeQuery = true)

List<Employee> findByDepartmentNative(@Param("dept") String dept);

Now:
- employee → table name
- department → column name

---

### 🔟 When to Use Native Query?

Use Native Query when:

✔ Complex joins

✔ Database-specific functions

✔ Performance optimization

✔ Stored procedures

✔ Window functions

✔ Grouping with advanced logic

Example:

@Query(value = "SELECT department, COUNT(*) as total FROM employee GROUP BY department", nativeQuery = true)

List<Object[]> getDepartmentCount();

#### 🧠 JPQL vs Native Query (Important Interview Question)

| Feature      | JPQL           | Native Query           |
| ------------ | -------------- | ---------------------- |
| Works on     | Entity         | Table                  |
| Portable     | Yes            | No                     |
| DB dependent | No             | Yes                    |
| Performance  | Good           | Best (complex queries) |
| Use case     | Business logic | Advanced SQL           |

---

### 🏆 Real Interview Questions (Must Prepare)
Q1: Difference between JPQL and Native Query?

→ Entity vs Table based.

Q2: Why @Modifying is required?

→ Because UPDATE & DELETE change data.

Q3: What happens if we don’t use @Transactional?

→ Changes may not commit.

Q4: Can JPQL use JOIN?

Yes.

@Query("SELECT e FROM Employee e JOIN e.department d WHERE d.name = :name")

---

### 🔥 Performance Tip (Expert Level)

Always:
- Use projections if you don't need full entity.
- Avoid SELECT *.
- Use pagination.

Example:

Page<Employee> findByDepartment(String dept, Pageable pageable);

---

### 🚀 Real Architecture Advice (20 LPA Level Thinking)

Munna, when building microservices:

✔ Use JPQL for business-level queries

✔ Use Native only when required

✔ Keep repository clean

✔ Move complex logic to service layer

✔ Prefer DTO projection for APIs


---

# Spring Boot – JPQL & Native Queries Interview Q&A

---

## 1️⃣ What is JPQL?

JPQL (Java Persistence Query Language) is an object-oriented query language used to query JPA entities.

- It works on **Entity class names**
- It uses **entity field names**
- It is database independent

Example:
@Query("SELECT e FROM Employee e WHERE e.department = :dept")

---

## 2️⃣ Difference Between JPQL and Native Query?

| Feature | JPQL | Native Query |
|----------|------|--------------|
| Works on | Entity class | Database tables |
| Uses | Field names | Column names |
| Portable | Yes | No |
| DB Specific | No | Yes |
| Complex SQL support | Limited | Full support |

---

## 3️⃣ Why is JPQL Database Independent?

Because JPQL works on entity abstraction.
Hibernate converts JPQL into SQL based on the configured database dialect.

---

## 4️⃣ What is @Query Annotation?

@Query allows writing custom JPQL or native SQL queries inside repository interfaces.

Example:
@Query("SELECT e FROM Employee e WHERE e.salary > :salary")

---

## 5️⃣ What is Named Parameter in JPQL?

Named parameters use `:parameterName`.

Example:
WHERE e.department = :dept

And bind using:
@Param("dept")

---

## 6️⃣ What is Positional Parameter in JPQL?

Uses `?1`, `?2` etc.

Example:
@Query("SELECT e FROM Employee e WHERE e.department = ?1")

Less readable than named parameters.

---

## 7️⃣ Why Do We Use @Modifying?

@Modifying is required for:

- UPDATE
- DELETE
- INSERT (native)

Without it, Spring assumes the query is SELECT.

---

## 8️⃣ Why is @Transactional Required with @Modifying?

Because update/delete operations must run inside a transaction.
Otherwise, changes may not commit.

---

## 9️⃣ Can JPQL Perform JOIN?

Yes.

Example:
@Query("SELECT e FROM Employee e JOIN e.department d WHERE d.name = :name")

JPQL joins use entity relationships.

---

## 🔟 What is Projection in JPQL?

Projection means selecting specific fields instead of full entity.

Example:
@Query("SELECT e.name, e.salary FROM Employee e")

Better approach:
@Query("SELECT new com.example.dto.EmployeeDTO(e.name, e.salary) FROM Employee e")

This improves performance.

---

## 1️⃣1️⃣ What is Native Query?

Native Query is plain SQL written inside @Query with:

nativeQuery = true

Example:
@Query(value = "SELECT * FROM employee WHERE salary > ?1", nativeQuery = true)

---

## 1️⃣2️⃣ When Should We Use Native Query?

- Complex joins
- Window functions
- Database-specific functions
- Performance-critical queries
- Stored procedures

---

## 1️⃣3️⃣ Can Native Query Return Entity?

Yes, if:

- Columns match entity fields
- Result mapping is correct

Otherwise, use projection.

---

## 1️⃣4️⃣ What Happens If Field Name is Wrong in JPQL?

Application fails at runtime.
Because JPQL validates entity field names.

---

## 1️⃣5️⃣ What is the Return Type of Update/Delete Query?

int

It returns number of rows affected.

Example:
int updateSalary(...)

---

## 1️⃣6️⃣ What is the Difference Between findByMethod and @Query?

Derived Query:
findByDepartment(String dept)

Custom Query:
@Query("SELECT e FROM Employee e WHERE e.department = :dept")

@Query is used for:
- Complex conditions
- Joins
- Aggregation
- Performance tuning

---

## 1️⃣7️⃣ Can We Use Pagination with JPQL?

Yes.

Page<Employee> findByDepartment(String dept, Pageable pageable);

Spring automatically adds LIMIT & OFFSET.

---

## 1️⃣8️⃣ What is Aggregation in JPQL?

Aggregation functions:

- COUNT()
- AVG()
- SUM()
- MIN()
- MAX()

Example:
@Query("SELECT COUNT(e) FROM Employee e")

---

## 1️⃣9️⃣ What is the Risk of Using Native Queries?

- Database dependency
- Harder to maintain
- Reduced portability

---

## 2️⃣0️⃣ How Does Hibernate Convert JPQL to SQL?

Hibernate uses Dialect (e.g., MySQLDialect, PostgreSQLDialect)
to convert JPQL into database-specific SQL at runtime.

---

# 🔥 Advanced Interview Question

Q: How to optimize query performance in Spring Boot?

Answer:
- Use projections instead of full entity
- Use pagination
- Avoid N+1 problem
- Use JOIN FETCH when needed
- Index database columns
- Use native query for heavy SQL logic

---

# ✅ Conclusion

JPQL is object-oriented and portable.
Native Query is powerful but database dependent.

A senior engineer knows:
- When to use JPQL
- When to switch to native
- How to optimize performance














