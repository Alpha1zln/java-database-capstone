# Architecture Specification: Smart Clinic Management System

---

## Section 1: Architecture Overview

- The **Smart Clinic Management System** is designed as a robust **three-tier web application** built on the **Spring Boot** framework to achieve clean separation of concerns, maintainability, and high scalability. 

- At the **Presentation Tier**, the application implements a hybrid delivery model: it utilizes **Spring MVC with Thymeleaf** to render server-side dynamic HTML views for administrative and doctor dashboards, while simultaneously exposing lightweight **RESTful APIs** that return JSON responses for client-side consumers, mobile applications, and patient-facing modules like appointment booking. 

- Incoming requests are routed through dedicated Thymeleaf and REST controllers into a centralized **Service Layer**—the core of the application tier—which coordinates end-to-end clinical workflows, enforces business validations, and isolates domain logic from data access mechanics.

- At the **Data Tier**, the system implements a **polyglot persistence strategy** that pairs relational and document-based databases to match specific data requirements. Structured core entities—such as Patients, Doctors, Admin profiles, and Appointments—are persisted in **MySQL** via **Spring Data JPA / Hibernate** (`@Entity`), ensuring strict ACID guarantees, foreign-key referential integrity, and atomic slot reservation to eliminate double-booking. Conversely, semi-structured clinical records and multi-item medical prescriptions are managed in **MongoDB** using **Spring Data MongoDB** (`@Document`), providing schema flexibility for evolving medication formats without costly SQL migrations. 

- Bound models are rendered dynamically in web views or serialized into JSON DTOs for API clients, and the entire multi-tier stack is containerized using **Docker** and automated via **GitHub Actions CI/CD** for consistent, cloud-ready deployment.


---


### Archr dig Gist

- The Smart Clinic Management System follows a three-tier architecture powered by Spring Boot, separating concerns across presentation, business logic, and data storage. 
- The Presentation Tier provides a hybrid interface, combining server-rendered Thymeleaf dashboards for staff administration with JSON-based RESTful APIs for decoupled patient and appointment modules. 
- The Application Tier routes requests through controllers into a dedicated Service Layer that executes core business logic and workflow coordination before delegating to repositories. 
- To optimize storage, the Data Tier leverages polyglot persistence: MySQL via Spring Data JPA provides ACID compliance and relational integrity for users, doctors, and transactional appointments, while MongoDB via Spring Data MongoDB stores flexible, document-based prescriptions and clinical notes. 
- The resulting models are bound to dynamic views or serialized as JSON, with the entire application packaged via Docker for automated testing and continuous integration through GitHub Actions.


---
---


## Section 2: Numbered flow of data and control (Steps 1 to 7) 
### Based directly on the provided architecture diagram:

---

1. **User Interaction at Presentation Layer**: Users access the application either through server-rendered browser views (such as `AdminDashboard` and `DoctorDashboard`) or via client-side/mobile REST consumer modules (such as `Appointments`, `PatientDashboard`, and `PatientRecord`).

2. **Request Routing to Controllers**: User actions and HTTP requests enter the Spring Boot backend and are routed by URL path and HTTP method to either a **Thymeleaf Controller** (for server-rendered web pages) or a **REST Controller** (for JSON API requests).

3. **Business Logic Execution (Service Layer)**: The controllers delegate the requests directly to the **Service Layer**, which coordinates workflows, validates incoming parameters, and executes clinical business rules (e.g., verifying doctor schedule availability before booking).

4. **Data Access via Repositories**: The service layer calls the dedicated **Repository Layer**, utilizing **MySQL Repositories** (Spring Data JPA) for structured relational entities and the **MongoDB Repository** (Spring Data MongoDB) for document-based records.

5. **Database Access & Execution**: The repositories query the underlying database engines: **MySQL** handles relational queries with ACID guarantees and foreign-key constraints (for patients, doctors, admins, and appointments), while **MongoDB** executes document reads and writes (for dynamic prescription records).

6. **Model Binding**: Retrieved database records are converted into strongly typed Java domain models—mapped into JPA Entities (`@Entity`) for MySQL table rows, and into Document objects (`@Document`) for MongoDB BSON/JSON collections.

7. **Response Delivery (Application Models in Use)**: The bound models or transformed DTOs are returned up the chain to generate the final response: Thymeleaf controllers bind the models to HTML templates to render dynamic web pages, while REST controllers serialize the data into JSON payloads returned to the client.


-----------

-----------

