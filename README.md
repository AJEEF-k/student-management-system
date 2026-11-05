# Student Management System

A RESTful web application developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL** to manage students and courses.

The application provides CRUD operations for students and courses, manages their relationship, and includes input validation and centralized exception handling.

## Technologies Used

- **Backend:** Java 21, Spring Boot
- **ORM:** Hibernate, Spring Data JPA
- **Database:** MySQL
- **API Testing:** Postman
- **Build Tool:** Maven
- **IDE:** Eclipse

## Features

### Student Management
- Add a new student.
- Retrieve all students.
- Retrieve a student by ID.
- Retrieve students by course ID.
- Update student details.
- Delete a student.
- Prevent duplicate student email addresses.

### Course Management
- Add a new course.
- Retrieve all courses.
- Retrieve a course by ID.
- Update course details.
- Delete a course.
- Prevent duplicate course names.
- Prevent deletion of courses assigned to students.

### Additional Features
- RESTful API development.
- DTO implementation for student request and response data.
- Input validation using Jakarta Bean Validation.
- Centralized exception handling using `@RestControllerAdvice`.
- Proper HTTP status codes for API responses.
- Entity relationships using JPA annotations.

## Project Architecture

The application follows a layered architecture:

```text
Controller Layer
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
MySQL Database
```

- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains business logic and validation checks.
- **Repository:** Handles database operations using Spring Data JPA.
- **Entity:** Represents database tables.
- **DTO:** Transfers request and response data.
- **Exception:** Handles application exceptions centrally.

## Database Design

The application contains two main entities:

### Student
- id (Primary Key)
- name
- email (Unique)
- age
- course_id (Foreign Key)

### Course
- courseId (Primary Key)
- courseName (Unique)

### Entity Relationship

A course can have multiple students, while each student belongs to one course.

```text
       COURSE
    -------------
    courseId (PK)
    courseName
         |
         | 1
         |
         | 
         | Many
       STUDENT
    -------------
    id (PK)
    name
    email
    age
    course_id (FK)
```

The relationship is implemented using `@ManyToOne` and `@JoinColumn` in the Student entity.

## REST API Endpoints

Base URL: `http://localhost:8080`

### Student APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/students` | Add a student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/course/{courseId}` | Get students by course |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

### Course APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/courses` | Add a course |
| GET | `/api/courses` | Get all courses |
| GET | `/api/courses/{courseId}` | Get course by ID |
| PUT | `/api/courses/{courseId}` | Update course |
| DELETE | `/api/courses/{courseId}` | Delete a course |

## Sample API Requests

### Add Course

**POST** `/api/courses`

```json
{
  "courseName": "Java Full Stack"
}
```

### Add Student

**POST** `/api/students`

```json
{
  "name": "John",
  "email": "john@example.com",
  "age": 22,
  "courseId": 1
}
```

### Sample Student Response

```json
{
  "id": 1,
  "name": "John",
  "email": "john@example.com",
  "age": 22,
  "courseId": 1,
  "courseName": "Java Full Stack"
}
```

## Validation and Exception Handling

The application uses Jakarta Bean Validation to validate student input.

Examples:
- Student name cannot be blank.
- Student name must contain between 3 and 50 characters.
- Email must have a valid format.
- Student age must be between 18 and 100.
- Course ID is required.

Centralized exception handling is implemented using `@RestControllerAdvice` to handle validation errors and application exceptions.

## Installation and Setup

### Prerequisites

- Java Development Kit (JDK) 21
- MySQL Server
- Eclipse IDE
- Maven
- Postman

### Steps to Run the Project

1. Clone the repository.

2. Open the project in Eclipse as an existing Maven project.

3. Create the MySQL database:

```sql
CREATE DATABASE student_management;
```

4. Configure the database URL, username, and password in `application.properties`. Use your own local database credentials.

5. Allow Maven to download the required dependencies.

6. Run the main application class:

`StudentManagementApplication.java`

7. Test the REST APIs using Postman.

## Future Improvements

- Implement DTOs for Course APIs.
- Add pagination and sorting.
- Implement authentication and authorization using Spring Security.
- Add automated unit and integration tests.

## Author

**Ajeef K**

Java | Spring Boot | MySQL | REST API Development
