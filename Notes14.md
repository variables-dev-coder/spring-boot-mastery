# 🌐 REST API Versioning — Expert Deep Dive

### 1️⃣ Why API Versioning Exists (Real Reason, Not Textbook)

Once your API is consumed by clients (mobile apps, frontend, partners):
- You cannot change request/response freely
- Even a small change = client app crash
- Mobile apps update slowly → old versions live for months

👉 Versioning = backward compatibility + safe evolution

---

### 2️⃣ What Exactly Is a “Breaking Change”?

You must create a new API version if you do any of these:

❌ Remove a field

❌ Rename a field

❌ Change data type

❌ Change URL structure

❌ Change validation rules

❌ Change response format

✅ Adding a new optional field → usually OK (no version bump)


---

### 3️⃣ REST API Versioning Strategies (Industry Standard)

There are 4 main strategies used in real systems 👇

#### 🔹 Strategy 1: URI Versioning (Most Common)

Example

/api/v1/users

/api/v2/users

Spring Boot Example

@RestController

@RequestMapping("/api/v1/users")

public class UserV1Controller {

    @GetMapping
    public String getUsersV1() {
        return "Users from V1";
    }
}

@RestController

@RequestMapping("/api/v2/users")

public class UserV2Controller {

    @GetMapping
    public String getUsersV2() {
        return "Users from V2 with extra fields";
    }
}

✅ Pros
- Very clear
- Easy to debug
- Easy to document
- Swagger friendly
- Used by Amazon, PayPal, Stripe

❌ Cons
- URL pollution
- Not “pure REST” (but nobody cares in real world 😄)

🎯 Industry Verdict:

👉 BEST & MOST USED

#### 🔹 Strategy 2: Request Parameter Versioning

Example

/api/users?version=1

/api/users?version=2

Spring Boot Example

@GetMapping(value = "/api/users", params = "version=1")

public String usersV1() {

    return "Users V1";
}

@GetMapping(value = "/api/users", params = "version=2")

public String usersV2() {

    return "Users V2";
}

✅ Pros
- Same URL
- Simple to implement

❌ Cons
- Hard to cache
- Version is hidden
- Confusing for clients

🎯 Industry Verdict:

👉 Rarely used in large systems

#### 🔹 Strategy 3: Header Versioning

Example

X-API-VERSION: 1

X-API-VERSION: 2

Spring Boot Example

@GetMapping(value = "/api/users", headers = "X-API-VERSION=1")

public String usersV1() {

    return "Users V1";
}

@GetMapping(value = "/api/users", headers = "X-API-VERSION=2")

public String usersV2() {

    return "Users V2";
}

✅ Pros
- Clean URLs
- REST-friendly
- No URL changes

❌ Cons
- Hard to test manually
- Swagger setup complex
- Version not visible

🎯 Industry Verdict:

👉 Used in internal microservices


#### 🔹 Strategy 4: Media Type / Accept Header (Most REST-Pure)

Example

Accept: application/vnd.myapp.v1+json

Accept: application/vnd.myapp.v2+json

Spring Boot Example

@GetMapping(value = "/api/users", produces = "application/vnd.myapp.v1+json")

public String usersV1() {

    return "Users V1";
}

@GetMapping(value = "/api/users", produces = "application/vnd.myapp.v2+json")

public String usersV2() {

    return "Users V2";
}

✅ Pros
- Most REST-correct
- Clean URLs

❌ Cons
- Very complex
- Hard for frontend & testers
- Rare in Indian companies

🎯 Industry Verdict:

👉 Used by REST purists, not startups

---

### 4️⃣ Which Strategy Should YOU Use? (Interview Answer)

💡 Perfect Answer:
> “In real-world Spring Boot applications, I prefer URI versioning because it’s simple, explicit, easy to document, and widely adopted in production systems.”

---

### 5️⃣ Versioning at Controller vs DTO Level (Senior Insight)
#### ❌ Bad Practice
- Same DTO for all versions
- if(version == 2) logic

✅ Good Practice

UserV1Response

UserV2Response

Each version has:
- Separate Controller
- Separate DTO

Separate Swagger group

---

### 6️⃣ Versioning + Swagger (Real Projects)

Typical setup:

/swagger-ui.html → v1

/swagger-ui.html → v2

- Never mix versions
- Each version documented separately

---

### 7️⃣ Version Deprecation Strategy (Very Important)

Real companies do this 👇

1️⃣ Release /v2

2️⃣ Mark /v1 as DEPRECATED

3️⃣ Notify clients

4️⃣ Keep /v1 for 3–6 months

5️⃣ Kill /v1

Example Header:

X-API-DEPRECATED: true

X-API-SUNSET: 2026-06-01

---

### 8️⃣ Microservices Perspective (Advanced)
- Internal APIs → Header versioning
- External APIs → URI versioning
- Gateway handles routing
- Services evolve independently

---

### 9️⃣ Common Interview Questions (Rapid Fire)

Q. Is API versioning mandatory?

Yes, once API is public or mobile-consumed.

Q. Which versioning strategy is best?

URI versioning (most practical).

Q. Should we version database schema?

No. API version ≠ DB version.

Q. Can one controller support multiple versions?

Technically yes, architecturally no.


---

### 🔥 Final Expert Summary
- Versioning = contract safety
- URI versioning = industry king
- Separate controllers & DTOs
- Deprecation is as important as creation
- Clean versioning = senior engineer mindset

---

# Spring Boot – REST API Versioning  
## Interview Questions & Answers (Expert Level)

---

## 1. What is REST API versioning?
**Answer:**  
REST API versioning is a strategy used to manage changes in APIs without breaking existing clients. It allows multiple versions of the same API to coexist so that consumers can migrate safely while the backend continues to evolve.

---

## 2. Why is API versioning important in real-world applications?
**Answer:**  
In production systems, APIs are consumed by web apps, mobile apps, and third-party clients. Any breaking change (field removal, renaming, data type change) can crash clients. Versioning ensures backward compatibility and controlled evolution of the API.

---

## 3. What is a breaking change in REST APIs?
**Answer:**  
A breaking change is any modification that causes existing clients to fail. Examples include:
- Removing or renaming fields
- Changing data types
- Modifying request/response structure
- Changing validation rules
- Altering endpoint URLs

---

## 4. Name the common REST API versioning strategies.
**Answer:**  
1. URI Versioning  
2. Request Parameter Versioning  
3. Header Versioning  
4. Media Type (Accept Header) Versioning  

---

## 5. Explain URI versioning with an example.
**Answer:**  
In URI versioning, the API version is part of the URL.

Example:
/api/v1/users
/api/v2/users


This approach is explicit, easy to understand, and widely used in real-world systems.

---

## 6. What are the advantages of URI versioning?
**Answer:**  
- Clear and visible versioning
- Easy to test and debug
- Simple to document with Swagger
- Widely adopted in industry
- Easy client adoption

---

## 7. What are the disadvantages of URI versioning?
**Answer:**  
- URL duplication
- Not strictly REST-pure
- Requires maintaining multiple controllers

---

## 8. What is request parameter versioning?
**Answer:**  
The API version is passed as a query parameter.

Example:
/api/users?version=1


Spring Boot maps requests based on the parameter value.

---

## 9. Why is request parameter versioning not preferred in large systems?
**Answer:**  
- Versioning is hidden and unclear
- Difficult to cache responses
- Confusing for API consumers
- Poor documentation clarity

---

## 10. What is header-based versioning?
**Answer:**  
The API version is sent using a custom HTTP header.

Example:
X-API-VERSION: 1


Spring Boot routes requests based on header values.

---

## 11. When is header versioning typically used?
**Answer:**  
Header versioning is often used in internal microservices communication where APIs are not exposed publicly and strict REST principles are preferred.

---

## 12. What is media type (Accept header) versioning?
**Answer:**  
The API version is specified using the `Accept` header with custom media types.

Example:
Accept: application/vnd.myapp.v1+json


This approach is considered the most REST-compliant.

---

## 13. Why is media type versioning rarely used in practice?
**Answer:**  
- Complex to implement and test
- Hard for frontend teams and manual testing
- Swagger configuration becomes difficult
- Overkill for most business applications

---

## 14. Which API versioning strategy is most commonly used in Spring Boot projects?
**Answer:**  
URI versioning is the most commonly used strategy due to its simplicity, clarity, and wide industry adoption.

---

## 15. Can we support multiple API versions in a single controller?
**Answer:**  
Technically yes, but it is not recommended. It leads to complex conditional logic and poor maintainability. Best practice is to use separate controllers and DTOs per version.

---

## 16. Should DTOs be shared across API versions?
**Answer:**  
No. Each API version should have its own DTOs to avoid accidental breaking changes and to clearly represent version-specific contracts.

---

## 17. How do you deprecate an API version?
**Answer:**  
- Mark the old version as deprecated
- Inform clients via documentation and headers
- Provide a migration timeline
- Maintain old version for a fixed period
- Eventually remove it

---

## 18. What headers are commonly used for API deprecation?
**Answer:**  
X-API-DEPRECATED: true
X-API-SUNSET: 2026-06-01


These inform clients about upcoming API retirement.

---

## 19. Should database schema be versioned along with APIs?
**Answer:**  
No. API versioning and database schema versioning are independent. The same database schema can support multiple API versions.

---

## 20. How does API versioning work in microservices architecture?
**Answer:**  
- External APIs use URI versioning
- Internal APIs often use header versioning
- API Gateway routes requests to correct versions
- Services evolve independently without breaking consumers

---

## 21. How does Swagger handle API versioning?
**Answer:**  
Each API version is documented separately, usually by grouping controllers or configuring multiple Swagger definitions. Versions should never be mixed in the same documentation.

---

## 22. Is API versioning mandatory for internal APIs?
**Answer:**  
Not always, but it is strongly recommended for APIs shared across teams or services to prevent breaking dependent systems.

---

## 23. What is the best interview answer for “Which versioning strategy do you use?”
**Answer:**  
“In real-world Spring Boot applications, I use URI versioning because it is explicit, easy to maintain, simple to document, and widely adopted in production systems.”

---

## 24. Can adding a new field require a new API version?
**Answer:**  
No, if the field is optional and does not break existing clients. Only breaking changes require a new version.

---

## 25. What does API versioning reflect in system design?
**Answer:**  
It reflects a mature system design mindset that prioritizes backward compatibility, client safety, and long-term maintainability.

---

## 26. One-line summary for interviews.
**Answer:**  
API versioning is a contract management strategy that allows APIs to evolve without breaking existing consumers.

---























