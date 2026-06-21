# Hexagonal Architecture

A simple Spring Boot multi-module project demonstrating **Hexagonal Architecture** / **Ports and Adapters**.

The goal of this project is to keep business logic independent from frameworks, database, web layer, and infrastructure details.

## Project Structure

```text
hexagonal-architecture
├── core
├── web-adapters
├── database-adapters
└── bootstrap
```

## Modules

### core

Contains the application core:

* Domain models
* Business rules
* Use cases
* Input ports
* Output ports
* Application services

The `core` module does not depend on Spring Web, JPA, MySQL, REST, or any adapter.

### web-adapters

Contains the input adapter for REST API.

Responsibilities:

* Receive HTTP requests
* Convert requests to application commands
* Call input ports / use cases
* Return HTTP responses

Depends on:

```text
web-adapters → core
```

### database-adapters

Contains the output adapter for persistence.

Responsibilities:

* JPA entities
* Spring Data repositories
* Persistence adapter implementations
* Mapping between domain models and database entities

Depends on:

```text
database-adapters → core
```

### bootstrap

Runnable Spring Boot application module.

Responsibilities:

* Main application class
* Runtime configuration
* Bean wiring
* Connecting all modules together

Depends on:

```text
bootstrap → core
bootstrap → web-adapters
bootstrap → database-adapters
```

## Dependency Direction

The most important rule:

```text
Dependencies always point toward the core.
```

```text
                    ┌──────────────────┐
                    │    bootstrap      │
                    │ Spring Boot App   │
                    └───────┬────┬─────┘
                            │    │
             ┌──────────────┘    └──────────────┐
             ▼                                  ▼
┌──────────────────────┐          ┌────────────────────────┐
│    web-adapters       │          │   database-adapters     │
│ Controller, Request   │          │ Entity, JpaRepository   │
└───────────┬──────────┘          └───────────┬────────────┘
            │                                 │
            └──────────────┬──────────────────┘
                           ▼
                    ┌────────────┐
                    │    core    │
                    │ business   │
                    └────────────┘
```

The core does not know who calls it or where data is stored.

```text
HTTP / REST      → web-adapters       → core
Database / JPA   → database-adapters  → core

core             → no adapter dependency
```

## Ports and Adapters

### Input side

Input adapters call application use cases through input ports.

```text
HTTP Request
    ↓
Web Adapter
    ↓
Input Port
    ↓
Use Case
    ↓
Domain Logic
```

### Output side

Application services access external systems through output ports.

```text
Use Case
    ↓
Output Port
    ↓
Persistence Adapter
    ↓
Database
```

## Business Case

The project uses a simple **Car Listing** example.

Main business actions:

* Create car listing
* Get car listing
* Approve car listing
* Reject car listing

Business rules:

* New listing is created with `PENDING` status.
* Only `PENDING` listing can be approved.
* Only `PENDING` listing can be rejected.
* Approved listing cannot be rejected.
* Rejected listing cannot be approved.

## Architecture Flow

```text
                  OUTSIDE WORLD
                       │
                       ▼
              ┌─────────────────┐
              │  web-adapters   │
              │  REST Adapter   │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │   Input Ports   │
              │    Use Cases    │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │      core       │
              │ Business Logic  │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │  Output Ports   │
              └────────┬────────┘
                       │
                       ▼
          ┌─────────────────────────┐
          │   database-adapters      │
          │ JPA / Persistence Layer  │
          └────────────┬────────────┘
                       │
                       ▼
                    MySQL
```

## Tech Stack

* Java 26
* Spring Boot 4.1.0
* Gradle Multi-Module
* Spring Web MVC
* Spring Data JPA
* MySQL
* Docker Compose
* Lombok

## Run Database

```bash
docker compose up -d
```

## Build

```bash
./gradlew build
```

Windows:

```bash
.\gradlew.bat build
```

## Run Application

```bash
./gradlew :bootstrap:bootRun
```

Windows:

```bash
.\gradlew.bat :bootstrap:bootRun
```

## Main Rule

```text
Business logic stays in the core.
Framework and infrastructure details stay outside as adapters.
```

Good dependency direction:

```text
web-adapters        → core
database-adapters   → core
bootstrap           → core + adapters
```

Bad dependency direction:

```text
core → web-adapters
core → database-adapters
core → Spring MVC
core → JPA
core → MySQL
```

## Summary

This project demonstrates how to structure a Spring Boot application using Hexagonal Architecture.

The core contains the business logic and defines what it needs through ports.

Adapters implement those ports using real technologies such as REST, JPA, and MySQL.

This keeps the application easier to test, maintain, and extend.
