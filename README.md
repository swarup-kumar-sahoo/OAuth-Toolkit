# OAuth-Toolkit

A complete Spring Boot Authentication System built using **Spring Security**, **JWT**, **OAuth2 (Google Login)**, and **MongoDB**.

OAuth-Toolkit demonstrates modern authentication and authorization practices by supporting both traditional email/password login and Google OAuth2 login while securing APIs using JWT tokens.

---

## Features

### Authentication

* User Registration
* User Login using Email & Password
* Password Encryption with BCrypt
* JWT Token Generation
* JWT Token Validation

### OAuth2 Login

* Google Sign-In Integration
* Automatic User Registration on First Login
* JWT Generation after Successful OAuth Login

### Authorization

* Role-Based Access Control (RBAC)
* USER Role
* ADMIN Role
* Protected API Endpoints

### Admin Management

* Create Admin Accounts using Secure PIN Verification
* Restricted Admin APIs

### Database

* MongoDB Integration
* User Persistence
* OAuth User Storage

---

## Security Features

* BCrypt Password Hashing
* JWT Authentication
* OAuth2 Authentication
* Stateless API Security
* Role-Based Authorization
* Protected Endpoints
* Secure Admin Creation

---

## Getting Started

### Clone Repository

```bash
git clone https://github.com/your-username/OAuth-Toolkit.git
```

### Install Dependencies

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

---

## Future Enhancements

* Refresh Tokens
* Email Verification
* Password Reset
* GitHub OAuth Login
* Role Management Dashboard

---

## Author

Swarup Kumar Sahoo

Built with Spring Boot, Spring Security, JWT, OAuth2, and MongoDB.


<img width="960" height="504" alt="image" src="https://github.com/user-attachments/assets/6c3e9f0b-2c9a-4195-ba5f-a36f42d3214f" />
