# User Registration API

Simple REST API for user registration and management.

This project was created for learning purposes, focusing on clean architecture, validation, and proper error handling using Spring Boot.

---

## About the project

This API allows basic user management operations.

The main goal is to practice backend fundamentals before moving to more advanced topics such as authentication and security.

---

## What you practice here

- REST API design  
- Layered architecture  
- DTO pattern  
- Bean Validation  
- Business rules  
- Custom exceptions  
- Centralized exception handling  
- JPA and database constraints  

---

## Technologies

- Java  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Jakarta Bean Validation  
- Maven  
- Relational Database  

---

## Features

- Create user  
- Update user  
- Delete user  
- Find user by id  
- List users with pagination  

---

## Business rules

- Email must be unique  
- Duplicate emails are not allowed  
- On update:
  - `name` and `email` are required  
  - `password` is optional  
  - If `password` is sent, it must be valid  

---

## Password rules

When provided, the password must:

- Have between 8 and 72 characters  
- Contain:
  - Uppercase letter  
  - Lowercase letter  
  - Number  
  - Symbol  

If `password` is not sent on update, the previous password is kept.

---

## Endpoints

### Get user by id

```http
GET /users/{id}
````

---

### List users

```http
GET /users
```

Supports pagination.

---

### Create user

```http
POST /users
```

Request body example:

```json
{
  "name": "MarcoDev",
  "email": "marco@email.com",
  "password": "Abc@1234"
}
```

---

### Update user

Without changing password:

```http
PUT /users/{id}
```

```json
{
  "name": "MarcoDev",
  "email": "marco@email.com"
}
```

With password change:

```json
{
  "name": "MarcoDev",
  "email": "marco@email.com",
  "password": "New@1234"
}
```

---

### Delete user

```http
DELETE /users/{id}
```

---

## Error handling

The API uses centralized exception handling with `@ControllerAdvice`.

### Possible responses

* **404 Not Found**
  User not found

* **400 Bad Request**
  Validation errors
  Referential integrity errors

* **409 Conflict**
  Email already registered

---

## Project structure

```text
controllers
services
repositories
dto
entities
exceptions
```

---

## Notes

* This is a learning-focused project
* Passwords are not encrypted
* No authentication or authorization
* Designed to practice backend fundamentals

---

## Next steps

* Encrypt passwords using BCrypt
* Add authentication with Spring Security
* Write unit tests
* Add API documentation with Swagger
