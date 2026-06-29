# TaskTracker Application Architecture

## 1. Overview

TaskTracker is a Spring Boot REST API application used for managing users and tasks.

The application implements CRUD operations:

* Create users
* Create tasks
* View tasks
* Update tasks
* Delete tasks
* Filter tasks
* Monitor application health

The application is designed as a production-style service suitable for DevOps and SRE practices.

---

# 2. High Level Architecture

```
               Client
                 |
                 |
        Swagger UI / Postman
                 |
                 |
        Spring REST Controllers
                 |
                 |
          Service Layer
                 |
                 |
        Spring Data Repository
                 |
                 |
           PostgreSQL Database

```

---

# 3. Application Layers

## Controller Layer

Package:

```
controller
```

Responsibilities:

* Handle HTTP requests
* Validate input
* Return HTTP responses

Controllers:

```
UserController
TaskController
HealthController
MetricsController
```

---

## Service Layer

Package:

```
service
```

Responsibilities:

* Business logic
* Validation
* Entity processing

Services:

```
UserService
TaskService
```

---

## Repository Layer

Package:

```
repository
```

Uses:

```
Spring Data JPA
```

Responsibilities:

* Database communication
* CRUD operations

Repositories:

```
UserRepository
TaskRepository
```

---

# 4. Database Architecture

Database:

```
PostgreSQL
```

Tables:

## users

| Column | Type      |
| ------ | --------- |
| id     | BIGSERIAL |
| name   | VARCHAR   |
| email  | VARCHAR   |

## tasks

| Column      | Type      |
| ----------- | --------- |
| id          | BIGSERIAL |
| title       | VARCHAR   |
| description | TEXT      |
| status      | VARCHAR   |
| due_date    | DATE      |
| created_at  | TIMESTAMP |
| owner_id    | BIGINT    |

Relationship:

```
User

 1

 |

 |

 *

Task

```

One user can own multiple tasks.

---

# 5. API Architecture

## User APIs

POST

```
/users
```

GET

```
/users/{id}
```

## Task APIs

POST

```
/tasks
```

GET

```
/tasks
```

GET

```
/tasks/{id}
```

PUT

```
/tasks/{id}
```

DELETE

```
/tasks/{id}
```

---

# 6. Health Monitoring

## Liveness

Endpoint:

```
GET /healthz
```

Purpose:

Checks application availability.

Response:

```json
{
 "status":"ok"
}
```

---

## Readiness

Endpoint:

```
GET /readyz
```

Purpose:

Checks:

* Application
* Database connection

Response:

```json
{
 "status":"ready"
}
```

---

# 7. Metrics

Endpoint:

```
GET /metrics
```

Provides application statistics.

Used by:

* Prometheus
* Grafana

---

# 8. Logging

RequestLoggingFilter provides:

* Request ID
* HTTP method
* URL path
* Response status
* Execution time

Example:

```json
{
"request_id":"abc123",
"method":"GET",
"path":"/tasks",
"status":200
}

```

---

# 9. Testing Strategy

Testing layers:

## Unit Testing

Tools:

* JUnit 5
* Mockito

Coverage:

* Services
* DTO
* Entities

---

## Controller Testing

Tool:

```
MockMvc
```

Tests:

* HTTP status
* JSON response
* Validation

---

## Coverage

Generated using:

```
JaCoCo
```

Command:

```
mvn verify
```

Report:

```
target/site/jacoco/index.html
```

---

# 10. Future Deployment

Current:

```
Spring Boot
    |
PostgreSQL

```

Future Kubernetes:

```
Ingress

 |

Service

 |

Pods

 |

PostgreSQL

```

The application is prepared for:

* Docker
* Kubernetes
* Prometheus
* Grafana
* CI/CD
