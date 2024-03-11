Maveric Bank Application

| Page       | Route                    |
|------------|--------------------------|
| Login      | /login                   |
| Dashboard  | /user/{customerId}       |
| AddAccount | /addaccount/{customerId} |
| AddUser    | /adduser                 |


This repository contains the Docker Compose configuration for deploying the Maveric Bank application, consisting of various microservices and an Angular frontend.

Microservices:

- Account Service: Manages user accounts and transactions. Exposes REST endpoints for account creation, retrieval, and updates.
- User Service: Handles user-related operations such as user creation, retrieval, and authentication.
- Transaction Service: Manages transaction-related functionalities, including transaction creation and retrieval.
- Eureka Service: Acts as the service registry for the application, facilitating service discovery and registration.
- Gateway Service: Serves as the API gateway, routing requests to appropriate microservices and managing traffic.
- Balance Service: Manages balance-related information and interacts with MongoDB to store and retrieve balance data.

Database Services:

- MySQL Account Database: Stores account-related data, including user account information and transaction details.
- MySQL User Database: Stores user-related data such as user credentials and profile information.
- MySQL Transaction Database: Stores transaction-related information, including transaction details and history.
- MongoDB: Stores balance-related data for the application.

Angular Frontend:

The Angular frontend application provides a user-friendly interface for interacting with the Maveric Bank application. It communicates with the backend microservices via RESTful APIs.

Deployment:

To deploy the Maveric Bank application, use Docker Compose to spin up the containers defined in the docker-compose.yml file. Ensure that Docker is installed on your system and run docker-compose up in the project directory.

Access the Angular frontend by navigating to http://localhost:8080 in your web browser.

Note: Before running the application, make sure to update any configuration files or environment variables as needed, particularly database connection details and service URLs.
