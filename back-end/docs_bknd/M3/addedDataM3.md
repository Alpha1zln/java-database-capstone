# Module 3 (M3) — Sample Data Ingestion & Database Initialization

## 📋 Overview
This document records the complete database setup, schema alignment, and sample data population for the Smart Clinic Management System (CMS) in **Module 3**. 

The system operates on a **Polyglot Persistence Architecture**:
* **MySQL (`cms` database):** Stores transactional, relational data requiring strict schema integrity (Admins, Doctors, Patients, Appointments, Doctor Availability).
* **MongoDB (`prescriptions` database):** Stores semi-structured clinical medical documents (Prescriptions).

---

## 🛠️ 1. Database Architecture & Table Alignment

### Hibernate Auto-DDL & Singular Table Mapping
During initial setup, Hibernate generated plural table names from the model annotations. Coursera's official data scripts require **singular table names**. We updated the `@Table` annotations across the model classes:

| Java Entity Model | Updated Annotation | MySQL Generated Table | Purpose |
| :--- | :--- | :--- | :--- |
| [`Admin.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Admin.java) | `@Table(name = "admin")` | `admin` | Portal administrator credentials |
| [`Doctor.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Doctor.java) | `@Table(name = "doctor")` | `doctor` | Healthcare specialist profiles |
| [`Doctor.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Doctor.java) | `@ElementCollection` | `doctor_available_times` | Doctor working time slots (1NF compliant) |
| [`Patient.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Patient.java) | `@Table(name = "patient")` | `patient` | Registered patient demographics |
| [`Appointment.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Appointment.java) | `@Table(name = "appointment")` | `appointment` | Booking bridge linking Doctor and Patient |
| [`Prescription.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Prescription.java) | `@Document(collection = "prescriptions")` | `prescriptions` *(MongoDB)* | Flexible prescription documents |

---

## 🗄️ 2. MySQL Sample Data Summary

All sample data was seeded from Coursera's official specification [`DATA-ADD.MD`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/docs_bknd/M3/DATA-ADD.MD).

### 📊 Ingested Record Counts

```text
+------------------------+-----+
| Table                  | cnt |
+------------------------+-----+
| admin                  |   1 |
| doctor                 |  25 |
| doctor_available_times | 100 |
| patient                |  25 |
| appointment            | 130 |
+------------------------+-----+
```

### 1. `admin` Table (1 Record)
* **Default Admin:** `username: admin`, `password: admin@1234`

### 2. `doctor` Table (25 Records)
* **Specialties Covered:**
  * Cardiologist: Dr. Emily Adams, Dr. Taylor Grant, Dr. Ella Moore, Dr. Mia Green, Dr. Lily Scott
  * Neurologist: Dr. Mark Johnson, Dr. Sam White, Dr. Leo Martin, Dr. Jack Baker, Dr. Lucas Evans
  * Orthopedist: Dr. Sarah Lee, Dr. Emma Clark, Dr. Ivy Jackson, Dr. Nora Walker, Dr. Grace Turner
  * Pediatrician: Dr. Tom Wilson, Dr. Olivia Davis, Dr. Owen Thomas, Dr. Liam Young, Dr. Ethan Hill
  * Dermatologist: Dr. Alice Brown, Dr. Henry Miller, Dr. Ava Hall, Dr. Zoe King, Dr. Ruby Ward

### 3. `doctor_available_times` Table (100 Records)
* Each doctor has 4 dedicated 1-hour slots assigned (e.g., `09:00-10:00`, `10:00-11:00`, `14:00-15:00`, `15:00-16:00`).

### 4. `patient` Table (25 Records)
* 25 registered clinic patients with valid email addresses, 10-digit phone numbers, residential addresses, and encrypted passwords.

### 5. `appointment` Table (130 Records)
* **Status 0 (Scheduled):** Upcoming appointments booked across doctors and patients with future timestamps.
* **Status 1 (Completed):** Past completed consultations with historical timestamps.

---

## 🍃 3. MongoDB Sample Data Summary

### Collection: `prescriptions`
* **Target Database:** `prescriptions`
* **Total Documents Ingested:** **24 Documents**
* **Connection String:** `mongodb://localhost:27017/prescriptions`

### Sample Ingested Document Structure:
```json
{
  "_id": ObjectId("6807dd712725f013281e7201"),
  "patientName": "John Smith",
  "appointmentId": 51,
  "medication": "Paracetamol",
  "dosage": "500mg",
  "doctorNotes": "Take 1 tablet every 6 hours.",
  "_class": "com.project.back_end.models.Prescription"
}
```

### The Polyglot Bridge:
* `appointmentId: 51` connects this MongoDB document directly back to `appointment.id = 51` in MySQL, maintaining cross-database auditability.

---

## 🔍 4. Verification Commands

### MySQL Verification Queries
```sql
USE cms;

-- 1. Verify tables
SHOW TABLES;

-- 2. Verify row counts
SELECT 'admin' AS tbl, count(*) AS cnt FROM admin
UNION ALL SELECT 'doctor', count(*) FROM doctor
UNION ALL SELECT 'doctor_available_times', count(*) FROM doctor_available_times
UNION ALL SELECT 'patient', count(*) FROM patient
UNION ALL SELECT 'appointment', count(*) FROM appointment;

-- 3. Verify relationships
SELECT a.id AS appt_id, d.name AS doctor_name, p.name AS patient_name, a.appointment_time, a.status
FROM appointment a
JOIN doctor d ON a.doctor_id = d.id
JOIN patient p ON a.patient_id = p.id
LIMIT 5;
```

### MongoDB Verification Queries (`mongosh`)
```javascript
use prescriptions

// 1. Verify collections
show collections

// 2. Verify total document count (Expected: 24)
db.prescriptions.countDocuments()

// 3. Find one sample document
db.prescriptions.findOne()

// 4. Query prescriptions for an appointment
db.prescriptions.find({ appointmentId: 51 }).pretty()
```

---

## ⚙️ 5. Configuration Reference ([`application.properties`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/resources/application.properties))

```properties
# MySQL Connection
spring.datasource.url=jdbc:mysql://localhost:3306/cms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update

# MongoDB Connection
spring.data.mongodb.uri=mongodb://root:db1234@localhost:27017/prescriptions?authSource=admin
```

---

## 📁 6. Companion Terminal Logs & Operation Scripts

Detailed live terminal session transcripts and CLI execution logs are recorded in the companion reference files:

* 📄 **[`sqlOpM3.md`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/docs_bknd/M3/sqlOpM3.md)**:  
  Contains the interactive PowerShell terminal transcript of MySQL authentication, `SHOW DATABASES;`, database switching, table schema verification (`DESCRIBE doctor;`, `DESCRIBE appointment;`), and sample data insertion queries.

* 📄 **[`mongodbOpM3.md`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/docs_bknd/M3/mongodbOpM3.md)**:  
  Contains the interactive PowerShell terminal transcript of `mongosh` session launch, `show dbs`, switching to `use prescriptions`, inspecting collections (`show collections`), and querying formatted documents via `db.prescriptions.find().pretty()`.

* 📄 **[`storedProceduresM3.md`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/docs_bknd/M3/storedProceduresM3.md)** & **[`stored_procedures.sql`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/docs_bknd/M3/stored_procedures.sql)**:  
  Contains definitions, layman explanations, and execution outputs for the 3 reporting stored procedures (`GetDailyAppointmentReportByDoctor`, `GetDoctorWithMostPatientsByMonth`, `GetDoctorWithMostPatientsByYear`).

---
*Created and verified as part of IBM Coursera Capstone Module 3 deliverables.*
