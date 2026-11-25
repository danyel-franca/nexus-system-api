# Nexus System API

REST API for **Nexus System**, a Kanban-style project and task management app built as an academic project. This API powers the React Native mobile app, handling users, projects and tasks stored in a MySQL database.

## Tech Stack

- **Java 21**
- **Spring Boot 4** (Web MVC, Data JPA, Validation)
- **MySQL** (via MySQL Connector/J)
- **BCrypt** password hashing (Spring Security Crypto)
- **Lombok**
- **Maven** (wrapper included)

## Features

- Full CRUD for users, projects and tasks
- User sign-up and login with BCrypt-hashed passwords (passwords are never returned in responses)
- Request validation with clear error messages
- Global error handling (400, 401, 404, 409)
- Tasks organized in three Kanban columns: `TODO`, `IN_PROGRESS` and `DONE`
- CORS enabled for web clients (Expo web)

## Getting Started

### Prerequisites

- Java 21+
- A MySQL database with the `users`, `projects` and `tasks` tables already created

### 1. Configure the database connection

Database credentials are kept out of version control. Copy the example file and fill in your own values:

```bash
cp banco.properties.example banco.properties
```

```properties
spring.datasource.url=jdbc:mysql://YOUR_HOST:3306/YOUR_DATABASE?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASSWORD
```

> `banco.properties` is listed in `.gitignore` and must never be committed.

### 2. Run the API

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080`.

## Endpoints

### Users — `/user`

| Method | Route | Description |
|---|---|---|
| GET | `/user/listar` | List all users |
| GET | `/user/listar/{id}` | Get a user by ID |
| POST | `/user/cadastrar` | Create a user |
| POST | `/user/login` | Log in with email and password |
| PUT | `/user/atualizar/{id}` | Update a user (partial) |
| DELETE | `/user/excluir/{id}` | Delete a user |

### Projects — `/project`

| Method | Route | Description |
|---|---|---|
| GET | `/project/listar` | List all projects |
| GET | `/project/listar/{id}` | Get a project by ID |
| GET | `/project/usuario/{userId}` | List a user's projects |
| POST | `/project/cadastrar?userId={id}` | Create a project for a user |
| PUT | `/project/atualizar/{id}` | Update a project (partial) |
| DELETE | `/project/excluir/{id}` | Delete a project and its tasks |

### Tasks — `/task`

| Method | Route | Description |
|---|---|---|
| GET | `/task/listar` | List all tasks |
| GET | `/task/listar/{id}` | Get a task by ID |
| GET | `/task/projeto/{projectId}` | List a project's tasks |
| GET | `/task/usuario/{userId}` | List all tasks from a user's projects |
| POST | `/task/cadastrar` | Create a task |
| PUT | `/task/atualizar/{id}` | Update a task (partial) |
| DELETE | `/task/excluir/{id}` | Delete a task |

## Request Examples

**Create a user** — `POST /user/cadastrar`
```json
{
  "name": "Ana Silva",
  "email": "ana@email.com",
  "password": "123456"
}
```

**Log in** — `POST /user/login`
```json
{
  "email": "ana@email.com",
  "password": "123456"
}
```

**Create a project** — `POST /project/cadastrar?userId=1`
```json
{
  "name": "Kanban App",
  "description": "College project",
  "color": "#3366FF"
}
```

**Create a task** — `POST /task/cadastrar`
```json
{
  "projectId": 1,
  "title": "Build login screen",
  "description": "Email and password form",
  "priority": "HIGH",
  "status": "TODO",
  "dueDate": "2026-12-01"
}
```

**Move a task to another column** — `PUT /task/atualizar/1`
```json
{
  "status": "IN_PROGRESS"
}
```

**Remove a task's due date** — `PUT /task/atualizar/1`
```json
{
  "clearDueDate": true
}
```

### Allowed values

| Field | Values |
|---|---|
| `status` | `TODO`, `IN_PROGRESS`, `DONE` |
| `priority` | `LOW`, `MEDIUM`, `HIGH` |
| `dueDate` | `YYYY-MM-DD` |
| `color` | `#RRGGBB` |

Update endpoints are partial: only the fields sent in the request are changed.

## Error Handling

| Status | When | Response body |
|---|---|---|
| 400 | Invalid fields | `[{ "campo": "email", "mensagem": "E-mail inválido." }]` |
| 400 | Malformed JSON or invalid enum value | `{ "mensagem": "..." }` |
| 401 | Wrong email or password on login | *(empty)* |
| 404 | Resource not found | *(empty)* |
| 409 | Duplicate email or duplicate project name for the same user | `{ "mensagem": "..." }` |

> Error messages are returned in Portuguese, as they are displayed directly in the app.

## Project Structure

```
src/main/java/com/nexussytem/Api/Rest
├── controller/     # REST endpoints (User, Project, Task)
├── infra/          # CORS configuration and global error handler
└── model/
    ├── user/       # Entity, repository and DTOs
    ├── project/    # Entity, repository and DTOs
    └── task/       # Entity, repository, DTOs and enums
```

## Notes

- This is an academic project. Endpoints are not protected by token-based authentication (e.g., JWT); the login endpoint validates credentials and returns the user data.
- Hibernate is set to `ddl-auto=none`, so the database schema must be created beforehand.

## Related

- **Mobile app:** React Native + Expo client that consumes this API.
