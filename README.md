User Registration API

Simple REST API for user registration and management.

This project was created for learning purposes, focusing on practicing core concepts of Spring Boot, JPA, DTOs, validation, and exception handling.

Project Goal

You practice:

Building REST APIs with Spring Boot

Layered architecture (Controller, Service, Repository)

Using DTOs for input and output

Bean Validation

Business rules with custom exceptions

Centralized exception handling

Transaction management

UNIQUE constraint in the database

Technologies

Java

Spring Boot

Spring Web

Spring Data JPA

Bean Validation (Jakarta Validation)

Relational database

Maven

Features

Find user by id

List users with pagination

Create user

Update user

Delete user

Business Rules

Email must be unique

It is not allowed to register two users with the same email

On update:

name and email are required

password is optional

If password is sent, it must be valid

Password Validation

When provided, the password must:

Have between 8 and 72 characters

Contain:

Uppercase letter

Lowercase letter

Number

Symbol

If the password is not sent on update, the previous password is kept.

Endpoints
Find user by id
