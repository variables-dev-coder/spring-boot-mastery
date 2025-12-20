# 🔐 Spring Security Interview Questions & Answers
- (Beginner → Advanced | Java Backend Focus)

## 1️⃣ What is Spring Security?

Answer: 

Spring Security is a powerful framework that provides authentication, authorization, and protection against common security attacks for Spring applications.
It works using a filter-based architecture that intercepts requests before they reach controllers.

## 2️⃣ Authentication vs Authorization?

Answer:

Authentication: Verifies who the user is (username & password)

Authorization: Verifies what the user can access (roles/permissions)

👉 Authentication comes before authorization.


## 3️⃣ How does Spring Security work internally?

Answer:

Spring Security uses a chain of filters called the Security Filter Chain.
Each request passes through these filters for:

- Authentication
- Authorization
- CSRF protection
- Session management

If a request fails any check, it never reaches the controller.


## 4️⃣ What is SecurityFilterChain?

Answer:

SecurityFilterChain defines security rules for incoming HTTP requests, such as:

- Which URLs are public
- Which require authentication
- Which require specific roles

It replaces WebSecurityConfigurerAdapter in Spring Security 6.


## 5️⃣ What is UserDetailsService?

Answer:

UserDetailsService is an interface used by Spring Security to load user data from a database during authentication.

Spring Security calls:

loadUserByUsername(username)

to validate credentials.

## 6️⃣ Why do we use BCryptPasswordEncoder?

Answer:

BCrypt is a one-way hashing algorithm used to securely store passwords.

Benefits:
- Passwords cannot be decrypted
- Salt is automatically added
- Resistant to brute-force attacks


## 7️⃣ Why does Spring Security protect all APIs by default?

Answer:

Spring Security follows a secure-by-default approach.

Without explicit configuration, it blocks all endpoints to prevent accidental data exposure.

## 8️⃣ What is CSRF? Why disable it sometimes?

Answer:

CSRF (Cross-Site Request Forgery) is an attack where a malicious site sends unauthorized requests on behalf of a logged-in user.

It is often disabled for REST APIs because:

- REST APIs are stateless
- They use tokens instead of sessions


## 9️⃣ Difference between 401 and 403?

Answer:

- 401 Unauthorized → User is not authenticated
- 403 Forbidden → User is authenticated but not authorized

## 🔟 What is ROLE_ prefix in Spring Security?

Answer:

Spring Security automatically adds ROLE_ prefix internally.

Example:

hasRole("ADMIN") → ROLE_ADMIN

If role is stored as ADMIN instead of ROLE_ADMIN, authorization fails.


## 1️⃣1️⃣ How did you test Spring Security?

Answer:

I tested Spring Security using:
- Browser testing for form login
- Postman for secured API testing
- Verified role-based access
- Validated authentication failures (401/403)
- Checked database data via H2 Console


## 1️⃣2️⃣ What is H2 Console & why was it blocked?

Answer:

H2 Console is a web-based UI for viewing in-memory databases.

Spring Security blocks it by default, so we must explicitly allow:

- /h2-console/**
- Disable CSRF
- Disable frame options

## 1️⃣3️⃣ What is Session-based Authentication?

Answer:

In session-based authentication:
- User logs in once
- Server creates a session (JSESSIONID)
- Session is used for subsequent requests
- 
Used mainly in web applications.

## 1️⃣4️⃣ How is REST API security different?

Answer:

REST APIs are usually stateless and use:
- JWT tokens
- OAuth2
- API keys

Sessions and form login are avoided.


## 1️⃣5️⃣ What is AuthenticationManager?

Answer:

AuthenticationManager is responsible for validating user credentials by:
- Calling UserDetailsService
- Comparing passwords
- Returning authentication status

## 1️⃣6️⃣ What are Filters in Spring Security?

Answer:

Filters intercept requests before controllers to perform:
- Authentication
- Authorization
- CSRF checks
- Exception handling

## 1️⃣7️⃣ How do you secure REST APIs in Spring Boot?

Answer:

By:

- Disabling form login
- Using token-based authentication (JWT)
- Securing endpoints using roles
- Testing APIs via Postman

## 1️⃣8️⃣ What are real-world Spring Security challenges?

Answer:

- Role mismatch (ROLE_ prefix issues)
- CSRF blocking tools like H2
- Session issues in Postman
- 401 vs 403 confusion
- Password encryption mistakes


## 1️⃣9️⃣ What did you implement in your project?

Answer:

I implemented:

- Custom authentication using database users
- Role-based authorization
- Password encryption
- H2 database integration
- Postman-based API testing

## 2️⃣0️⃣ Why is Spring Security important?

Answer:

- Spring Security protects applications from:
- Unauthorized access
- Credential theft

Common security vulnerabilities
and is mandatory for production-grade backend systems.











