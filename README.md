# TaskTracker

TaskTracker is a Spring Boot REST API for managing users and tasks.

## Features

* Create User
* Get User By ID
* Create Task
* Get All Tasks
* Filter Tasks By Status
* Filter Tasks By Owner
* Update Task
* Delete Task
* Health Check Endpoint
* Readiness Check Endpoint
* PostgreSQL Integration
* Swagger UI Documentation

---

## Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Swagger OpenAPI

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── entity
│   │   ├── dto
│   │   ├── exception
│   │   └── logging
│   │
│   └── resources
│       ├── application.properties
│       └── db/migration
│
└── test
```

## Database Setup

Create PostgreSQL database:

```sql
CREATE DATABASE tasktracker;
```

---

## Environment Variables

Create a `.env` file using `.env.example`.

Required variables:

```env
SERVER_PORT=8080

DB_URL=jdbc:postgresql://localhost:5432/tasktracker

DB_USERNAME=postgres

DB_PASSWORD=root

LOG_LEVEL=INFO
```

---

## Running Application

Build:

```bash
mvn clean install
```

Run:

```bash
mvn spring-boot:run
```

---

## Swagger UI

Open:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Available Endpoints

### Users

```http
POST /users
GET /users/{id}
```

### Tasks

```http
POST /tasks
GET /tasks
GET /tasks/{id}
PUT /tasks/{id}
DELETE /tasks/{id}
```

### Health

```http
GET /healthz
GET /readyz
```

### Metrics

```http
GET /metrics
```

---

## Running Tests

```bash
mvn test
```

---

## Troubleshooting

### Database Connection Error

Verify:

* PostgreSQL is running
* Database exists
* Username/password are correct

### Port Already In Use

Change:

```env
SERVER_PORT=8081
```

and restart application.
