# Banking-Microservices-Suite

## Overview
This project demonstrates a microservices-based architecture by creating two interconnected microservices: Customer Management Service and Account Management Service. These services communicate with each other to manage customers and their accounts efficiently.The primary functionalities implemented in this project are:

## Customer Management Service:
Handles CRUD operations for customers.
Validates customer details for operations involving accounts.

## Account Management Service:
Manages customer accounts, including creation, deposit, and withdrawal of funds.
Ensures that any account operation (deposit or withdrawal) requires valid customer details.

# Key Features

## Inter-Service Communication:
The Customer Management Service and Account Management Service communicate to ensure data consistency.
When a customer is deleted, all associated accounts are also deleted to maintain data integrity.

## Validation of Customer Details:
Before performing deposit or withdrawal operations, the customer details provided are validated against the Customer Management Service.
This ensures that only valid customers can perform transactions on their accounts.

## Cascade Delete Functionality:
Implemented a cascade delete mechanism where deleting a customer triggers the deletion of all related accounts.

# Technical Overview
The project utilizes the following key components and technologies:

**Spring Boot**: For building the microservices.
**Eureka Server**: For service discovery and registration.
**Config Server**: For centralized configuration management.
**API Gateway**: For routing requests to the appropriate microservices.
**MySQL**: As the relational database management system.

## Services
1. **Customer Management Service**
2. **Account Management Service**
3. **API Gateway**
4. **Config Server**
5. **Eureka Server**


**CUSTOMER MANAGEMENT SERVICE**
-Create Customer: 	POST http://localhost:8081/customers

-Get all Customers: 	GET http://localhost:8081/customers

-Get Single Customer:	GET http://localhost:8081/customers/customerId

-Update Customer: 	PUT http://localhost:8081/customers/customerId
-Delete Customer: 	DELETE http://localhost:8081/customers/customerId


**ACCOUNT MANAGEMENT SERVICE**
-Create Account: 	     POST http://localhost:8082/accounts
-Get all Accounts: 	     GET http://localhost:8082/accounts
-Get Account by Acc no:      GET http://localhost:8082/accounts/accountNumber
-Get Accounts by customerId: GET http://localhost:8082/accounts/customerId
-Add Money to Account:	     PUT http://localhost:8082/accounts/depositMoney
-Withdraw Money from Account:PUT http://localhost:8082/accounts/withdrawMoney
-Delete Account: 	     DELETE http://localhost:8082/accounts/accountNumber

**API GATEWAY**
All requests to the microservices should be routed through the API Gateway:

-Create Customer:	     POST http://localhost:8083/customers
-Get all Customers: 	     GET http://localhost:8083/customers
-Get Single Customer:	     GET http://localhost:8083/customers/customerId
-Update Customer: 	     PUT http://localhost:8083/customers/customerId
-Delete Customer: 	     DELETE http://localhost:8083/customers/customerId
-Create Account: 	     POST http://localhost:8083/accounts
-Get all Accounts: 	     GET http://localhost:8083/accounts
-Get Account by Acc no:      GET http://localhost:8083/accounts/accountNumber
-Get Accounts by customerId: GET http://localhost:8083/accounts/customerId
-Add Money to Account:	     PUT http://localhost:8083/accounts/depositMoney
-Withdraw Money from Account:PUT http://localhost:8083/accounts/withdrawMoney
-Delete Account: 	     DELETE http://localhost:8083/accounts/accountNumber

## Conclusion
This project provides a robust example of a microservices architecture where services interact to maintain data integrity and ensure valid operations. The cascade delete functionality and customer validation before account operations highlight key aspects of inter-service communication and data consistency in microservices.
