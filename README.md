# E-Commerce Platform

This is a microservices-based e-commerce platform, built using Spring Boot, Spring Cloud, Kafka, Vaadin, and PostgreSQL. The project is designed to handle common e-commerce operations, such as managing users, products, orders, and payments, while leveraging event-driven communication and real-time monitoring.

## Table of Contents

- [Architecture](#architecture)
- [Modules Overview](#modules-overview)
  - [Admin Module](#admin-module)
  - [Auth Module](#auth-module)
  - [Catalog Module](#catalog-module)
  - [Config Module](#config-module)
  - [Discovery Module](#discovery-module)
  - [Gateway Module](#gateway-module)
  - [Monitoring Module](#monitoring-module)
  - [Notification Module](#notification-module)
  - [Order Module](#order-module)
  - [Payment Module](#payment-module)
  - [Review Module](#review-module)
  - [User Module](#user-module)
  - [Common Module](#common-module)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Event-Driven Communication](#event-driven-communication)
- [Monitoring](#monitoring)
- [Testing](#testing)
- [Contributors](#contributors)

## Architecture

The platform uses a microservice architecture where each module is a standalone service. **Kafka** is used for event-driven communication, while **Spring Cloud** enables service discovery, load balancing, and API gateway management.

![Architecture Diagram](path_to_architecture_diagram.png)

## Modules Overview

### Admin Module

The Admin module provides a user-friendly UI for managing the entire system. It uses **Vaadin** to build the interface and **Spring Boot Admin** for service monitoring.

- **Views:**
  - Catalog Management
  - User Management
  - Order Management
  - Review Management
  - Event Logs (Real-time Kafka events)

- **Key Technologies:**
  - Vaadin for UI
  - Spring Boot Admin for monitoring
  - Kafka for event consumption

### Auth Module

Handles authentication and authorization for the platform, including JWT-based login and token validation.

- **Features:**
  - JWT-based authentication
  - User login and registration
  - Token validation

### Catalog Module

Manages products, categories, and tags in the e-commerce system. Supports product addition, editing, and deletion.

- **Features:**
  - Product CRUD operations
  - Category and tag management
  - Kafka-driven stock updates when orders are placed

### Config Module

Handles centralized configuration management for all services using **Spring Cloud Config**.

- **Features:**
  - Centralized configuration management
  - Configuration updates without redeploying services

### Discovery Module

The **Eureka Discovery Service** registers all microservices and provides service discovery and load balancing.

- **Features:**
  - Service discovery
  - Dynamic scaling of services

### Gateway Module

The API Gateway module routes requests to appropriate microservices and secures endpoints.

- **Features:**
  - Central entry point for all services
  - JWT token-based security
  - Load balancing and routing

### Monitoring Module

Responsible for collecting and exposing metrics of all services using **Micrometer** and **Prometheus**.

- **Features:**
  - Prometheus integration
  - Metrics collection from Kafka and services
  - Health and performance monitoring

### Notification Module

Handles user notifications, such as order confirmations and alerts.

- **Features:**
  - Kafka event-based notifications
  - Email and SMS notifications via third-party services

### Order Module

Handles order creation, updates, and cancellation. Integrates with the Payment and Catalog services.

- **Features:**
  - Order creation and cancellation
  - Payment integration
  - Stock updates via Kafka

### Payment Module

Processes payments for orders using external payment gateways like Stripe and PayPal.

- **Features:**
  - Payment gateway integration (Stripe, PayPal)
  - Event-driven payment updates

### Review Module

Allows users to add and manage product reviews. Integrates with the User and Catalog modules.

- **Features:**
  - Review creation and deletion
  - Kafka events for review creation

### User Module

Manages user profiles, authentication, and registration. Includes password encryption using PBKDF2 with HMAC SHA-512.

- **Features:**
  - User registration and profile management
  - Secure password handling
  - Feign clients for communication with other services

### Common Module

Shared code across all services, including DTOs, utility classes, and exception handling.

## Tech Stack

- **Java 21+**
- **Spring Boot**
- **Spring Cloud (Eureka, Gateway, Config)**
- **Kafka**
- **PostgreSQL**
- **Vaadin** (for Admin UI)
- **Spring Boot Admin**
- **Micrometer & Prometheus** (for monitoring)
- **Feign Clients** (for inter-service communication)
- **Docker & Docker Compose**

## Getting Started

### Prerequisites

- Java 21+
- Docker and Docker Compose
- Maven 4+
- Postgresql, Kafka and Zookeeper running locally or via Docker Compose

### Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/e-commerce-platform.git
   cd e-commerce-platform
   ```

2. Build the project:

```bash
mvn clean install
```

3. Start the services using Docker Compose:

```bash
    docker-compose up
```

4. Access the services:

        Admin UI: http://localhost:8081/admin
        API Gateway: http://localhost:8080

API Endpoints

Here are some key API endpoints for different services:
Auth Module

    POST /login - Login user and return JWT

Catalog Module

    GET /products - Fetch all products
    POST /products - Add a new product

Order Module

    GET /orders - Get user orders
    POST /orders - Create a new order

User Module

    GET /profile/{id} - Fetch user profile
    POST /register - Register a new user

Review Module

    POST /reviews - Add a new product review

Event-Driven Communication

This project uses Kafka for real-time event-driven communication between microservices. Key events include:

    Order Created: Notifies the Catalog and Payment services.
    Stock Update: Notifies the Catalog service to update product stock.

Monitoring

The Monitoring module is integrated with Prometheus and Spring Boot Admin. You can view real-time metrics and health checks for all services from the Admin module UI.

    Prometheus: Available at http://localhost:9090
    Admin UI: Available at http://localhost:8081/admin

Testing
The platform uses JUnit 5 for unit and integration tests. Each service can be tested independently. For the CI/CD pipeline, Docker Compose is used to run a local environment with PostgreSQL and Kafka.

To run the tests:

```bash

mvn test

```

License

This project is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 4.0 International License - see the [LICENSE][cc-by-nc-nd] file for details.

[![CC BY-NC-ND 4.0][cc-by-nc-nd-image]][cc-by-nc-nd]

[cc-by-nc-nd]: http://creativecommons.org/licenses/by-nc-nd/4.0/
[cc-by-nc-nd-image]: https://licensebuttons.net/l/by-nc-nd/4.0/88x31.png
[cc-by-nc-nd-shield]: https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg
