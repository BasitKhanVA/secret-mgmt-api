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
```

Replace `your-vault-token` with your actual Vault token.

To run the application, use the following command:

```
mvn exec:java -Dexec.mainClass="gov.va.bip.secretmgmt.Application"
```

The application will start and listen on `http://localhost:8080`.

## API Endpoints

1. Health Check
    - URL: `GET /health`
    - Response: `{"status": "UP"}`

2. Get Secret
    - URL: `GET /secrets/{key}`
    - Response:
        - 200 OK: `{"key": "secretKey", "value": "secretValue"}`
        - 404 Not Found: `{"error": "Secret not found"}`

## Swagger Documentation

Swagger documentation is available at:

```
http://localhost:8080/openapi.json
```

You can view the Swagger UI by copying the content of this JSON and pasting it into the Swagger Editor at https://editor.swagger.io/.

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
   curl http://localhost:8080/secrets/dummyKey
   ```
   Expected response: `{"key":"dummyKey","value":"dummyValue"}`

4. Test retrieving a non-existent secret:
   ```
   curl http://localhost:8080/secrets/nonexistentKey
   ```
   Expected response: `{"error": "Secret not found"}` with a 404 status code

5. Verify Swagger documentation:
    - Open `http://localhost:8080/openapi.json` in a browser
    - Confirm that the JSON content describes the API endpoints

6. Test with different environment variables:
    - Stop the application
    - Change the `VAULT_ADDR`, `VAULT_TOKEN`, or `VAULT_SECRET_PATH` environment variables
    - Restart the application and confirm it logs the new values (with token redacted)

7. Confirm JSON responses:
    - All responses should be in JSON format
    - Check the `Content-Type` header in responses is `application/json`

Note: The current implementation uses dummy data for secrets. Integration with an actual Vault instance is pending.

## Known Limitations

- The application is not yet fully prepared for cloud deployment.
- Actual integration with HashiCorp Vault is not implemented; the application currently returns dummy data.