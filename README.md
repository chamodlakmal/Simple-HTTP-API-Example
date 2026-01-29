# Simple HTTP API

## Overview

This is a simple Java Spring Boot application exposing a single HTTP endpoint:

- **GET /hello-world?name=alice**

### Behavior

- If the first letter of `name` is A–M or a–m: returns 200 OK, `{ "message": "Hello Alice" }`
- If the first letter is N–Z or n–z: returns 400 Bad Request, `{ "error": "Invalid Input" }`
- If `name` is missing or empty: returns 400 Bad Request, `{ "error": "Invalid Input" }`

## How to Run the Application

1. Ensure you have Java 11+ and Maven installed.
2. In the project root, run:
   ```sh
   ./mvnw spring-boot:run
   ```
   or (if using Windows):
   ```sh
   mvnw.cmd spring-boot:run
   ```
3. The API will be available at `http://localhost:8080/hello-world?name=YourName`

## How to Run the Tests

Run the following command in the project root:

```sh
./mvnw test
```

## Assumptions

- Only the first character of the `name` parameter is checked.
- The check is case-insensitive.
- Leading/trailing spaces in `name` are ignored.
- The response capitalizes only the first letter of the name, leaving the rest as-is.

---

Thank you!
