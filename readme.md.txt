# Microservices Project

## Overview
This project demonstrates a microservices-based architecture by creating two interconnected microservices: Customer Management Service and Account Management Service. These services communicate with each other to manage customers and their accounts efficiently.

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

**EUREKA SERVER**
Ensure the Eureka server is running on port 8761 and the service URLs are correct in configuration files.


**CONFIG SERVER**
The Config Server fetches its configurations from a GitHub repository. Ensure that the GitHub repository URL is correctly set in the application.yml or application.properties file of the Config Server.


**Database Setup**
Ensure that your MySQL server is running and the necessary databases are created. Update the database configurations in the application.yml or application.properties files accordingly.