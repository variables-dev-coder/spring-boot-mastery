# Spring Boot Day 37

## Request Params • Path Variables • Headers

---

### 1️⃣ @RequestParam — Query Parameters

👉 Used for optional / filter / search type data

👉 Comes after ? in URL

#### ✅ Example URL
GET /users?age=25&city=Hyderabad

#### ✅ Controller Code

@GetMapping("/users")

public String getUsers(

        @RequestParam int age,
        @RequestParam String city) {
    return "Age: " + age + ", City: " + city;
}

#### 🔑 Key Points
- Optional by default ❌ → required = true
- Can set default value

@RequestParam(defaultValue = "18") int age

#### 🧠 Use When
✔ Filtering

✔ Pagination

✔ Search APIs

---

### 2️⃣ @PathVariable — URL Path Data

👉 Used when value is mandatory and identifies resource

#### ✅ Example URL
GET /users/101

#### ✅ Controller Code

@GetMapping("/users/{id}")

public String getUser(@PathVariable int id) {

    return "User ID: " + id;
}

#### 🔥 Multiple Path Variables

@GetMapping("/users/{id}/orders/{orderId}")

public String getOrder(

        @PathVariable int id,
        @PathVariable int orderId) {
    return "User " + id + ", Order " + orderId;
}

#### 🧠 Use When

✔ Resource identification

✔ RESTful APIs

✔ CRUD endpoints


---

### 3️⃣ @RequestHeader — HTTP Headers

👉 Read metadata sent by client

(ex: auth token, content-type)

#### ✅ Example

@GetMapping("/info")

public String getHeader(

        @RequestHeader("User-Agent") String userAgent) {
    return "User-Agent: " + userAgent;
    
}

#### 🔥 Optional Header

@RequestHeader(value = "Authorization", required = false)

String token

#### 🧠 Use When

✔ JWT tokens

✔ API versioning

✔ Client info

---

### 4️⃣ ALL TOGETHER (Real-World API)

@GetMapping("/products/{id}")

public String getProduct(

        @PathVariable int id,
        @RequestParam(defaultValue = "INR") String currency,
        @RequestHeader("User-Agent") String agent) {

    return "Product: " + id +
           ", Currency: " + currency +
           ", Client: " + agent;
}

---

### 5️⃣ Interview GOLD 💰
❓ @PathVariable vs @RequestParam

| Feature    | PathVariable | RequestParam  |
| ---------- | ------------ | ------------- |
| Mandatory  | ✅ Yes        | ❌ Optional    |
| Location   | URL path     | Query string  |
| REST Style | ✅ Best       | ⚠️ Limited    |
| Example    | `/users/1`   | `/users?id=1` |

❓ Can we use both together?

✅ YES (Very common)

❓ Which is faster?

⚡ Same — difference is design, not performance.

---

### 6️⃣ Common Mistakes ❌
- Missing {} in URL
- Wrong param name
- Header key case mismatch
- Forgetting required=false

---

### 7️⃣ Practice Task 🧪 (Must Do)

Create APIs:
1. /employees/{id}
2. /search?name=&dept=
3. /secure → read Authorization header

Test everything in Postman.

---




