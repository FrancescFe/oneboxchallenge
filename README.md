# OneBox Software Engineer Test

## Table of Contents

1. [Abstract](#abstract)
2. [Technologies Used](#technologies-used)
3. [Approach to the Solution](#approach-to-the-solution)
4. [Pending Topics](#pending-topics)
5. [Possible Improvements](#possible-improvements)
6. [How to Run the Application](#how-to-run-the-application)
7. [How to Test the Application](#how-to-test-the-application)

## 📌Abstract

This project is a solution to the OneBox Software Engineer technical test.
It involves simulating the functionality of an e-commerce system.
To achieve this we implement a RESTful API using clean architecture principles to manage `Product` and `Cart` entities.
The API supports CRUD operations for both entities while adhering to industry standards and best practices, such as
`API-first development`, validation, robust error handling and testing.

## 🔎Technologies Used

- **Programming Language:** Java 21 🍵
- **Framework:** Spring Boot 3.4.0 (Spring Web, Spring Data JPA, Hibernate) 🍃
- **Database:** H2 (In-memory database) 🔋
- **Build Tool:** Maven 🪶
- **API Specification:** OpenAPI (openapi-generator-maven-plugin)
- **Validation:** Hibernate Validator (Jakarta Validation API)
- **Testing:** JUnit 5, MockMvc 🪲
- **Postman:** A workspace is created (for demo and manual testing)
- **GitHub Workflows:** Added an Action to validate Pull Requests
- **Docker:** optional for an easier deployment 🐋

## 💡Approach to the Solution

1. **API-First Design:**
    - Defined endpoints and data models using OpenAPI specifications.
    - Autogenerated DTOs and interfaces using `openapi-generator-maven-plugin`.

2. **Hexagonal Architecture:**
    - Divided the project into `application`, `domain`, and `infrastructure` layers for better maintainability and
      separation of concerns.
    - Used ports and adapters to decouple business logic from infrastructure.
    - Applied [Vertical Slice](https://www.jimmybogard.com/vertical-slice-architecture/) to minimize coupling between
      Products and Carts.

3. **Entity Relationships:**
    - Implemented a `Product` entity, representing individual items in the catalog.
    - Designed a `Cart` entity with a `ManyToMany` relationship with `Product`, allowing multiple products to belong to
      multiple carts.

4. **Validation and Error Handling:**
    - Applied constraints at the OpenAPI Specification level for data validation.
    - Used a `GlobalExceptionHandler` for consistent error responses.

5. **Testing:**
    - Developed integration tests for the endpoints using MockMvc.
    - Verified data persistence with H2 and tested invalid scenarios.

6. **Incremental Development:**
    - Followed an iterative approach by first implementing `Api`, then `Product` functionality and last extending it to
      `Cart`.

## ⏳Pending Topics

1. **Improve Test Coverage:**
    - Expand unit tests to cover edge cases more comprehensively.
    - Improve integration tests bad path coverage.
    - Add unit tests to mappers.

2. **Security:**
    - Authentication and authorization mechanisms (e.g., JWT) are not implemented.
    - To achieve this, `e-commerce.yaml` should be updated including a SecuritySchema.

3. **Code Refactor:**
    - Extract mappers into a new class.

4. **Caching:**
    - Introduce in-memory caching (e.g., Caffeine) for frequently accessed data like product details.

5. **API Documentation UI:**
    - UI integration for interactive API documentation (e.g.: Swagger UI, Stoplight, Redocly).

6. **OpenAPI Refactor:**
    - Refactor `e-commerce.yaml` extracting and reusing schemas, responses, requests and errors.

## ✏️Possible Improvements

1. **Testing Enhancements:**
    - Include contract tests to validate API compatibility with consumers.
    - Include a Quality Assurance folder to test in depth the use cases.

2. **Monitoring:**
    - Integrate tools like Prometheus and Grafana for performance monitoring and trace logs.

3. **API Versioning:**
    - Introduce versioning to maintain backward compatibility.

4. **API Decouple:**
    - Extract OpenApi generation and data into a spec repository.

5. **Data Persistence:**
    - Transition from H2 to a production-grade database (e.g.: PostgreSQL or MySQL).

6. **CI/CD:**
    - Add environments and GitHub Actions to improve the CI/CD

## 🚀How to Run the Application

1. **Requirements**:
    - [Docker](https://docs.docker.com/engine/install/)
2. **Clone the Repository:**
   ```
   git clone https://github.com/FrancescFe/oneboxchallenge.git
   cd oneboxchallenge
   ```
3. **Build the Project and Run the Application:**
   ```
   docker compose up -d
   ```
4. **Access the API:**
    - The Application runs at `http://localhost:8080`
    - [Postman workspace](https://www.postman.com/material-operator-40700101/workspace/onebox-challenge) created for
      testing/demo purpose

## 🧪How to Test the Application

- This Postman workspace can be used:

```
https://www.postman.com/material-operator-40700101/workspace/onebox-challenge
```

![img.png](readme_resources/postman_post_cart.png)

Although in the Integration Tests data is inserted into the in-memory database using a `schema.sql` and `data.sql`, outside the context of the tests, no data insertion has been automated. It must be done manually.
