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
- Wallet Service: Listens to Kafka events of user creation from user-service and publishes wallet balances updates events
- Transaction Service: Handles transactions and publishes transaction creation event and listen to wallet update events from wallet service
- Apache Kafka: Acts as the event broker for asynchronous communication

## Tech Stack
- Backend: Java | Spring Boot
- Messaging Queue: Apache Kafka
- Database: MySQL
- Build Tool: Maven

## Kafka Event Flow
1. User Created → 'user-created' event published
2. Wallet Service consumes event → user-created
3. Transaction Initiated → transaction-created' event published
4. Wallet service consumes event transaction-created'
5. Wallet Service updates sender & receiver balances and publishes event 'wallet-updated'
6. Transaction Service consume ''wallet-updated' event and mark the transaction status 'SUCCESS' or 'FAILED' based on the wallet updated status from wallet-updated event message.

## Setup Instructions
1. Clone the repository
2. Start Kafka and Zookeeper /Kafka only (if Kraft mode)
3. Configure application properties
4. Run individual microservices
5. Hit the User creation api and then transactions api

## Future Enhancements
- Transaction rollback support
- Wallet transaction history
- Notification Service -- Publish transaction completed event (which will be listened by a notification service to send out relevant communication)
- Authentication & authorization
- Monitoring with Prometheus & Grafana

## Author
- KRISHAV
