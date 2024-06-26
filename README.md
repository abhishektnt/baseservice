# Gravity Fast Commerce Base Project

This is a base project that will be used throughout the product development of Gravity Fast Commerce.

## Dependencies

This project utilizes the following dependencies:

- Lombok
- Actuator
- Micrometer-influx
- SLF4J
- Kafka
- Redis
- PostgreSQL
- MongoDB
- OpenAPI-3 (Swagger replacement)
- Spring Boot 3.2.4
- Open JDK 17

## Building and Running the Project

To build the project into a JAR file, use the following Maven commands from the project root directory:

```
mvn clean package
```

This will build a JAR file in the `target` directory. You can run the JAR file using the following command:

```
java -jar target/fastcommerce-0.0.1-SNAPSHOT.jar
```

## Postman Collection

A Postman collection is also provided with this project. You can import it into Postman by clicking on the "Import" button and selecting the `base-app.postman_collection.json` file. The collection contains all API endpoints and sample requests/responses.

## OpenAPI Base URL

The OpenAPI documentation for this project can be viewed in a browser by visiting the following URL:

```
http://localhost:8080/swagger-ui/index.html
```

This URL will display an interactive documentation page with all the available endpoints, descriptions, request and response formats, as well as the ability to test the APIs directly from the browser. Note: Make sure to run the project locally using `java -jar` before accessing the OpenAPI documentation in a browser.