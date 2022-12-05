# Tasteful Recipes

This Spring Boot application is a RESTful service to manage recipes in a CRUD fashion.

### Prerequisites

1. Java 17 (`JAVA_HOME` environment variable must be set)
2. Docker

### Build

From the terminal, enter the command: `./mvnw clean verify`

### Start DB

From the terminal, enter the
command: `docker run -d --rm --name postgres -e POSTGRES_USER=root -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=app-db -p 51289:5432 postgres:13.6`

### Run

From the terminal, enter the command: `./mvnw spring-boot:run`

### View

In your modern browser, go to http://localhost:8080/swagger-ui.html

### Implementation details

* initialized at https://start.spring.io with Maven as build tool.
* using Lombok to work around the verbosity of Java.
* relying on Spring for input validation.
* using JPA Hibernate to manage access to the database.
* using PostgresSQL as database, even in tests (via TestContainers) to avoid discrepancy between test and production.
* using JUnit5 with Mockito and AssertJ for fluent assertions.
* using Swagger for REST API docs.
* relying on Spring Boot pagination.
* relying on Hibernate filters and custom filtering implementation for the search.
* simple database design: each recipe has many ingredients (with quantity) and instructions.

### Possible enhancements

* use Flyway to manage database migrations because it has version control
* use ModelMapper to convert between database entities and DTOs
* implement caching to reduce load on the database
* build and package the app in a container with Jib
* add logging and metrics
* create environment profiles
* add more tests
* create slugs for recipes
