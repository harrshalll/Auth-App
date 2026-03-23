# Auth-App
# 🔐 Auth App Backend (Spring Boot + JWT)

A secure authentication and authorization backend built using **Spring Boot**, implementing **JWT (JSON Web Token)** based authentication with role-based access control.

---

## 🚀 Features

* 🔑 User Registration & Login
* 🔐 JWT-based Authentication (Access + Refresh Tokens)
* 🛡️ Role-based Authorization (Spring Security)
* 📦 Stateless Session Management
* 🔄 Refresh Token Mechanism
* 🗂️ User Management with Database Integration
* ⚙️ Custom Security Filter (JWT Filter)

---

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Security**
* **JWT (io.jsonwebtoken - JJWT)**
* **JPA**
* **PostgreSQL**
* **Maven**

---

## 📁 Project Structure (Updated)

```
src/main/java/com/demo/Auth_app_backend/
│
├── config/               # Security configurations
├── controller/           # REST APIs
├── entities/             # JPA entities
├── repositories/         # Database access layer
├── security/             # JWT & Filters
├── service/              # Business logic
├── dto/                  # Data Transfer Objects
├── exception/            # Custom exceptions & global handlers
│   ├── GlobalExceptionHandler.java   # Handles all exceptions globally
│   ├── ResourceNotFoundException.java
│   ├── BadRequestException.java
│   ├── UnauthorizedException.java
│   └── ApiError.java                # Standard error response structure
```

---

## ⚠️ Exception Handling Overview

* Centralized exception handling using `@ControllerAdvice`
* Custom exceptions for better readability and control
* Consistent API error responses

---

## 🧱 Example Components

### 1. Custom Exception


---

### 2. Global Exception Handler


---

### 3. Error Response DTO

---

## 🎯 Benefits

* Cleaner controllers (no try-catch everywhere)
* Standardized error responses
* Easy debugging
* Production-ready structure

---

---

## 🔐 Authentication Flow

1. User registers or logs in
2. Server validates credentials
3. JWT Access Token + Refresh Token generated
4. Client sends token in header:

   ```
   Authorization: Bearer <token>
   ```
5. JWT Filter validates token on each request
6. User is authenticated and authorized

---

## 📌 API Endpoints

### 🔓 Public Endpoints

| Method | Endpoint              | Description       |
| ------ | --------------------- | ----------------- |
| POST   | /api/v1/auth/register | Register new user |
| POST   | /api/v1/auth/login    | Login user        |

---

### 🔒 Protected Endpoints

| Method | Endpoint        | Description      |
| ------ | --------------- | ---------------- |
| GET    | /api/v1/users   | Get all users    |
| GET    | /api/v1/profile | Get user profile |

👉 Requires JWT Token

---

## ⚙️ Configuration (application.yml)

```yaml
security:
  jwt:
    secret: your-super-secret-key-should-be-long
    access-ttl-seconds: 3600
    refresh-ttl-seconds: 86400
    issuer: auth-app
```

---

## 🧠 Key Concepts

* **JWT Signing** using HMAC SHA-256
* **Custom Filter** extends `OncePerRequestFilter`
* **SecurityContextHolder** stores authentication
* Stateless API (No sessions used)

---

## ▶️ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/auth-app.git
   ```

2. Navigate to project:

   ```bash
   cd auth-app
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. Server starts at:

   ```
   http://localhost:8080
   ```

---

## 🧪 Testing

Use tools like:

* Postman
* Thunder Client

Add header:

```
Authorization: Bearer <your_token>
```

---

## 🔒 Security Notes

* Use strong secret key (min 32+ chars)
* Never expose JWT secret publicly
* Use HTTPS in production
* Implement token revocation if needed

---

## 📌 Future Improvements

* OAuth2 / Social Login
* Email Verification
* Password Reset Flow
* Rate Limiting
* API Documentation (Swagger)


---

## 👨‍💻 Author

**Harsh Avhad**

---

⭐ If you found this useful, give it a star!
