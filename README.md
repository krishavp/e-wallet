# E-Wallet Application

## Overview
The E-Wallet Application is an event-driven microservices-based system that manages digital wallets for users.  
When a user is created, a wallet is automatically generated using asynchronous communication via Apache Kafka.

All wallet transactions between users are also processed asynchronously using Kafka, ensuring scalability, fault tolerance, and loose coupling between services.

## Features
- Automatic wallet creation on user registration (Kafka-based async flow)
- Peer-to-peer wallet transactions between users
- Asynchronous transaction processing using Apache Kafka
- Real-time wallet balance updates
- Scalable and decoupled microservice architecture

## Architecture
- User Service: Creates users and publishes wallet creation events
- Wallet Service: Listens to Kafka events and manages wallet balances
- Transaction Service: Handles transactions and publishes balance update events
- Apache Kafka: Acts as the event broker for asynchronous communication

## Tech Stack
- Backend: Java / Spring Boot (if applicable)
- Messaging Queue: Apache Kafka
- Database: (MySQL)
- Build Tool: Maven

## Kafka Event Flow
1. User Created → `USER_CREATED` event published
2. Wallet Service consumes event → Wallet created
3. Transaction Initiated → `TRANSACTION_CREATED` event published
4. Wallet Service updates sender & receiver balances

## Setup Instructions
1. Clone the repository
2. Start Kafka and Zookeeper
3. Configure application properties
4. Run individual microservices

## Future Enhancements
- Transaction rollback support
- Wallet transaction history
- Authentication & authorization
- Monitoring with Prometheus & Grafana

## Author
- KRISHAV
