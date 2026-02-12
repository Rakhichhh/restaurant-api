
## Bonus Task — In-Memory Cache

A simple in-memory cache was implemented to improve performance.

The method GET /api/menu-items is cached. The first request loads data
from the database and stores it in memory. Subsequent requests return
data from cache instead of querying the database again.

The cache is implemented as a Singleton class using an in-memory Map.

Cache is automatically invalidated after menu items are created, updated,
or deleted to keep data consistent.

# Restaurant API — Spring Boot REST + JDBC

## Author
**Myrzabekov Rakhat**

---

## Project Overview
Restaurant API is a Spring Boot RESTful backend for managing menu items and customer orders.  
The system demonstrates layered architecture (Controller–Service–Repository), JDBC database access,
and implementation of creational design patterns: Singleton, Factory, and Builder.

The project integrates REST endpoints, database operations, SOLID principles, and component principles
into a clean backend architecture.

---

## Technology Stack
- Java 17
- Spring Boot
- Spring Web (REST controllers)
- Spring JDBC (JdbcTemplate)
- H2 Database (in-memory)
- Maven

---

## How to Run
1. Open project in IntelliJ IDEA.
2. Run `RestaurantApiApplication`.
3. API runs at:

http://localhost:8080

http://localhost:8080/api/menu-items

    


Optional:
H2 console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password empty 
for check request SELECT * FROM menu_items;
SELECT * FROM orders;



---

## REST API Documentation

### Menu Items
| Method | Endpoint | Description |
|---------|---------|-------------|
| GET | /api/menu-items | Get all items |
| GET | /api/menu-items/{id} | Get item by id |
| POST | /api/menu-items | Create item |
| PUT | /api/menu-items/{id} | Update item |
| DELETE | /api/menu-items/{id} | Delete item |

Example request:
```json
{
  "name": "Burger",
  "price": 1800,
  "isAvailable": true
}
Orders
Method	Endpoint	Description
POST	/api/orders	Create order
GET	/api/orders/{id}	Get order with items

Example order request:

json

{
  "customerName": "Ali",
  "orderType": "DELIVERY",
  "address": "Abay 10",
  "items": [
    { "menuItemId": 1, "quantity": 2 }
  ]
}
Example response:

json

{ "orderId": 1 }
Postman Screenshots
Screenshots are stored in:

bash

docs/screenshots/
Examples:

menu_get_all.png

menu_post.png

menu_put.png

menu_delete.png

order_post.png

order_get_by_id.png

error_example.png

Design Patterns Implementation
Singleton — LoggingService
A single logging instance is shared across the application.
Used in OrderService to log order creation.

Factory — OrderFactory
Validates rules for different order types:

DELIVERY requires address

DINE_IN requires table number

Helps extend system with new order types.

Builder — Order.Builder
Used to construct complex Order objects step-by-step,
supporting optional parameters and fluent creation.

Component Principles (REP, CCP, CRP)
REP — Reuse/Release Equivalence Principle
Reusable components are organized into modules such as repository, service, and patterns.

CCP — Common Closure Principle
Classes that change together are grouped together,
e.g., OrderService and OrderRepositoryJdbc.

CRP — Common Reuse Principle
Controllers depend only on services and not directly on database logic.

SOLID & OOP Summary
SRP: Controller handles HTTP, Service handles business logic, Repository handles database access.

DIP: Dependencies injected via Spring constructor injection.

Encapsulation applied in domain models.

Clean separation of layers ensures maintainability.

Database Schema
Tables:

menu_items (restaurant menu)

orders (customer orders)

order_items (items in orders)

Relationship:
One Order contains multiple OrderItems.

System Architecture


Client (Postman)
        ↓
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer (JdbcTemplate)
        ↓
Database (H2)
Reflection
This project demonstrates building a RESTful backend system using Spring Boot and JDBC,
applying design patterns and component principles, and structuring code in a maintainable,
scalable layered architecture suitable for real-world backend services.

