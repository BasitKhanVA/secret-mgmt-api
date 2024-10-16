# Secret Management RESTful API

This project is a RESTful API for secret management, implemented in Java without using Spring. It uses HashiCorp Vault for storing and retrieving secrets.

## Features

- RESTful API for secret management
- Non-Spring implementation using Jersey and Jetty
- Swagger documentation for API endpoints
- Environment variable configuration for Vault integration
- JSON responses for all endpoints

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- HashiCorp Vault instance (for actual secret management)

## Building the Project

To build the project, run the following command in the project root directory:

```
mvn clean install
```

## Running the Application

Before running the application, set the following environment variables:

```
export VAULT_ADDR="http://127.0.0.1:8200"
export VAULT_TOKEN="your-vault-token"
export VAULT_SECRET_PATH="secret/data/myapp"
vault kv put secret/data/myapp test-key="test-value"
```

Replace `your-vault-token` with your actual Vault token.

To run the application, use the following command:

```
mvn exec:java -Dexec.mainClass="gov.va.bip.secretmgmt.Application"
```

The application will start and listen on `http://localhost:8080`.

## Testing the Application

For QA testing, follow these steps:

1. Ensure the application is running as described in the "Running the Application" section.

2. Test the health check endpoint:
   ```
   curl http://localhost:8080/health
   ```
   Expected response: `{"status": "UP"}`

3. Test retrieving an existing secret:
   ```
   curl http://localhost:8080/secrets/test-key
   ```
   Expected response: `{"key":"test-key","value":"test-value"}`

4. Test retrieving a non-existent secret:
   ```
   curl http://localhost:8080/secrets/nonexistentKey
   ```
   Expected response: `{"error": "Secret not found"}` with a 404 status code

5. Verify Swagger documentation:

    - Open `http://localhost:8080/openapi.json` in a browser
    - Confirm that the JSON content describes the API endpoints