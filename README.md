# HOP Air Backend

A Spring Boot backend application for managing flight operations, courses, and user roles in the HOP Air system.

## Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security** - JWT-based authentication and authorization
- **Spring Data JPA** - Database access and ORM
- **MySQL** - Relational database
- **Maven** - Build and dependency management

## Project Structure

```
src/main/java/com/hopair/
├── HopairApplication.java          # Main Spring Boot application entry point
├── config/                          # Configuration classes
│   ├── SecurityConfig.java          # Spring Security and JWT configuration
│   └── AudienceValidator.java       # JWT audience validation
├── controller/                      # REST API endpoints
│   ├── AuthController.java          # Authentication endpoints
│   ├── UserController.java          # User management endpoints
│   ├── AgencyController.java        # Agency management endpoints
│   ├── BranchController.java        # Branch management endpoints
│   ├── CourseController.java        # Course management endpoints
│   └── LessonController.java        # Lesson management endpoints
├── service/                         # Business logic layer
│   ├── AuthService.java
│   ├── UserService.java
│   ├── AgencyService.java
│   ├── BranchService.java
│   ├── CourseService.java
│   └── LessonService.java
├── repository/                      # Data access layer (JPA repositories)
│   ├── UserRepository.java
│   ├── AgencyRepository.java
│   ├── BranchRepository.java
│   ├── CourseRepository.java
│   └── LessonRepository.java
├── entity/                          # JPA entity classes
│   ├── User.java
│   ├── Agency.java
│   ├── Branch.java
│   ├── Course.java
│   └── Lesson.java
├── dto/                             # Data Transfer Objects
│   ├── UserDTO.java
│   ├── AgencyDTO.java
│   ├── BranchDTO.java
│   ├── CourseDTO.java
│   ├── LessonDTO.java
│   ├── CreateUserRequest.java
│   ├── CreateAgencyRequest.java
│   ├── CreateBranchRequest.java
│   ├── CreateCourseRequest.java
│   └── CreateLessonRequest.java
└── enums/                           # Enumeration classes
    ├── Role.java                    # User roles (ADMIN, INSTRUCTOR, etc.)
    └── TargetRole.java

src/main/resources/
├── application.properties           # Application configuration
├── hopair_schema.sql               # Database schema definition
└── test-data.sql                   # Initial test data
```

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.0+

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd hop-air-backend
```

### 2. Configure Database

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hopair_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 3. Initialize Database

Run the SQL scripts to create the database schema and load test data:

```bash
mysql -u root -p < src/main/resources/hopair_schema.sql
mysql -u root -p hopair_db < src/main/resources/test-data.sql
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` (or the port configured in `application.properties`).

## API Endpoints

### Authentication
- `POST /auth/login` - User login
- `POST /auth/register` - User registration
- `POST /auth/refresh` - Refresh JWT token

### Users
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `POST /users` - Create new user
- `PUT /users/{id}` - Update user
- `DELETE /users/{id}` - Delete user

### Agencies
- `GET /agencies` - Get all agencies
- `GET /agencies/{id}` - Get agency by ID
- `POST /agencies` - Create new agency
- `PUT /agencies/{id}` - Update agency
- `DELETE /agencies/{id}` - Delete agency

### Branches
- `GET /branches` - Get all branches
- `GET /branches/{id}` - Get branch by ID
- `POST /branches` - Create new branch
- `PUT /branches/{id}` - Update branch
- `DELETE /branches/{id}` - Delete branch

### Courses
- `GET /courses` - Get all courses
- `GET /courses/{id}` - Get course by ID
- `POST /courses` - Create new course
- `PUT /courses/{id}` - Update course
- `DELETE /courses/{id}` - Delete course

### Lessons
- `GET /lessons` - Get all lessons
- `GET /lessons/{id}` - Get lesson by ID
- `POST /lessons` - Create new lesson
- `PUT /lessons/{id}` - Update lesson
- `DELETE /lessons/{id}` - Delete lesson

## Authentication

The application uses JWT (JSON Web Tokens) for authentication. Include the token in the `Authorization` header:

```
Authorization: Bearer <your_jwt_token>
```

## User Roles

The system supports the following roles (defined in `enums/Role.java`):
- `ADMIN` - Full system access
- `INSTRUCTOR` - Can manage courses and lessons
- `STUDENT` - Can view and enroll in courses
- `AGENCY_MANAGER` - Can manage agency and branches

## Database Schema

The application uses the following main entities:
- **User** - System users with roles and authentication
- **Agency** - Aviation agency or organization
- **Branch** - Branch of an agency
- **Course** - Training courses
- **Lesson** - Individual lessons within a course

See `hopair_schema.sql` for the complete database schema definition.

## Development

### Running Tests

```bash
mvn test
```

### Building for Production

```bash
mvn clean package
```

The JAR file will be created in the `target/` directory.

## License

This repository currently does not specify a license.
