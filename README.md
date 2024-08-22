# Empirical Sheltered Library Management System

## Description
The Empirical Library Management System is a Spring Boot-based application designed to manage book inventory and user data efficiently. 
It features robust CRUD operations for books and user management, along with authentication and authorization using JWT.

## Features

**Book Management:** Add, update, retrieve, and delete books.
**User Management:** Create, update, and authenticate users.
**Security:** Secure endpoints with JWT-based authentication.
**Database:** Relational database support for storing book and user information.

## Technologies Used

### Backend

- **Spring REST API** - Framework for building the backend API
- **Spring Security:** - For authentication and authorization.
- **JWT** - For secure token-based authentication.
- **PostgreSQL** - For database management
- **Lombok** - For reducing boilerplate code

### Libary Management
**GET /api/books/:**
**GET /api/books/select/{id}:**
**POST /api/books/create : Request Body: BookDto:**
**PUT /api/books/update/{id}: Request Body: BookDto**
**DELETE /api/books/delete/{id}:**

### User Management
> [!NOTE]
> Create User
> Authenticate User
**POST /api/users/create
Request Body: UserDto:**

**POST /api/authenticate
Request Body: LoginDto**

## Security

The application uses JWT for authentication. When accessing secured endpoints, include the JWT token in the Authorization header as follows.
```Authorization: Bearer <your-jwt-token>```

## WT Authentication Guidelines
**Create a User**
``Use the /api/users/create endpoint to register a new user. Ensure that the user details include a valid email and passwor``

**Authenticate**
```
Send a POST request to /api/authenticate with the following request body:
{
  "email": "user@example.com",
  "password": "yourpassword"
}

On successful authentication, the response will include a JWT token

{
  "token": "your.jwt.token.here"
}
```

### Example Response:
> [!IMPORTANT]
> 200 OK: If the token is valid and the request is authorized.
> 401 Unauthorized: If the token is missing, invalid, or expired.
> 403 Forbidden: If the token is valid but the user does not have permission to access the endpoint.

> [!TIP]
> The application uses JWT for authentication. Make sure to keep your JWT tokens secure and do not expose them in client-side code or logs
> JWT tokens are generally short-lived for security reasons. If you encounter a 401 Unauthorized error, the token may have expired. In such cases, you need to re-authenticate to obtain a new token.

  ## Getting Started
### Prerequisites

```
Java 17 or higher
Maven or Gradle
MySQL or PostgreSQL database
An IDE (e.g., IntelliJ IDEA, Eclipse)
```

### Installation

``` git clone https://github.com/yourusername/empirical-library-management-system.git ```
``` cd empirical-library-management-system ```

```
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

**Database Setup**
> [!IMPORTANT]
>  Ensure PostgreSQL is installed and running.
> Create a database named libary_db.


**Run The Application**
```./mvnw spring-boot:run```



### Contributing

## Fork the Repository:
**We welcome contributions to enhance the project! Follow these steps to contribute:
Click the "Fork" button at the top right of the repository page.**

## Create a New Branch:
```git checkout -b feature/your-feature```

## Make Your Changes:
Implement your feature or bug fix.

## Commit Your Changes:
```git commit -m "Add feature: your feature description"```

## Push To The Branch
```git push origin feature/your-feature```

## Create a Pull Request:
Open a pull request on GitHub and describe your changes.
