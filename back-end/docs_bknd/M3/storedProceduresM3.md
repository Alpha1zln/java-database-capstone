# Module 3, Unit 2, Chapter 3 — Adding Stored Procedures

## 📋 Lab Overview & Objectives
* **Module:** Module 3 (Databases & Tables) — Unit 2, Chapter 3
* **Lab Name:** Adding Stored Procedures
* **Target Database:** MySQL `cms` (Clinic Management System)
* **Associated Models:** [`Appointment.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Appointment.java), [`Doctor.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Doctor.java), [`Patient.java`](file:///F:/7-proj/smart_clinic_proj_coursera/smart_clinic_baseCode_proj/java-database-capstone/back-end/src/main/java/com/project/app/models/Patient.java)

### 🎯 Key Learning Objectives
1. **Write SQL Stored Procedures** to automate clinic operational and managerial report generation.
2. **Utilize Multi-Table SQL JOINs** across `appointment`, `doctor`, and `patient` tables.
3. **Execute Procedures with Dynamic Input Parameters** (`report_date`, `input_month`, `input_year`).
4. **Aggregate & Group Metrics** using `COUNT()`, `MONTH()`, `YEAR()`, `GROUP BY`, and `ORDER BY`.

---

## 🔑 Key Concepts & Terminology

| Term | Simple Real-World Analogy | Technical Definition |
| :--- | :--- | :--- |
| **Stored Procedure** | A saved **recipe** in the database kitchen. Instead of sending all ingredients and instructions across the network each time, you just call the recipe name with parameters. | A pre-compiled collection of SQL statements stored directly in the database server, executable via `CALL procedure_name(args)`. |
| **`DELIMITER $$`** | Changing the sentence-ending punctuation from a period `.` to a double dollar `$$` so the database doesn't stop reading halfway through a multi-line paragraph. | Tells the MySQL client to change the default statement terminator (`;`) temporarily, preventing premature execution before the procedure `END` block. |
| **`IN Parameter`** | An input slot on a vending machine where you insert your coin or selection. | Input variables passed into the procedure at runtime (e.g., `IN report_date DATE`). |
| **`JOIN`** | Connecting two puzzle pieces using matching tabs. | Combines columns from two or more tables based on foreign key relationships (`a.doctor_id = d.id`, `a.patient_id = p.id`). |
| **`GROUP BY` & `COUNT()`** | Sorting clinic files into doctor folders and counting how many patient folders are inside each. | Groups matching rows together and aggregates summary statistics per group. |

---

## 🛠️ Stored Procedure Definitions & Layman Explanations

### 1. `GetDailyAppointmentReportByDoctor`
* **Purpose:** Generates a daily schedule for all clinic doctors on a specific date. Displays the doctor's name, appointment time, status, patient name, and patient contact phone number.
* **Tables Joined:** `appointment a` ⟕ `doctor d` (on `a.doctor_id = d.id`) ⟕ `patient p` (on `a.patient_id = p.id`).
* **Input Parameter:** `IN report_date DATE`

```sql
DELIMITER $$
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(
    IN report_date DATE
)
BEGIN
    SELECT 
        d.name AS doctor_name,
        a.appointment_time,
        a.status,
        p.name AS patient_name,
        p.phone AS patient_phone
    FROM appointment a
    JOIN doctor d ON a.doctor_id = d.id
    JOIN patient p ON a.patient_id = p.id
    WHERE DATE(a.appointment_time) = report_date
    ORDER BY d.name, a.appointment_time;
END $$
DELIMITER ;
```

#### 📖 Line-by-Line Explanation:
* **Line 1 (`DELIMITER $$`):** Changes MySQL's command terminator from `;` to `$$`. This ensures MySQL doesn't prematurely execute the internal statements before the whole procedure is defined.
* **Line 2 (`CREATE PROCEDURE GetDailyAppointmentReportByDoctor(`):** Defines the stored procedure name in the MySQL database catalog.
* **Line 3 (`IN report_date DATE`):** Specifies an incoming parameter named `report_date` of type `DATE` (`YYYY-MM-DD`). The `IN` keyword means it is read-only input.
* **Line 4 (`)`):** Closes parameter definition.
* **Line 5 (`BEGIN`):** Marks the start of the procedure's executable SQL logic (like `{` in Java).
* **Line 6 (`SELECT`):** Declares which columns should appear in the resulting report table.
* **Line 7 (`d.name AS doctor_name,`):** Selects the doctor's name from table alias `d` (`doctor`) and renames the column to `doctor_name`.
* **Line 8 (`a.appointment_time,`):** Selects the date & timestamp when the appointment is scheduled from table alias `a` (`appointment`).
* **Line 9 (`a.status,`):** Selects the status code (`1` = Confirmed/Scheduled, `0` = Cancelled).
* **Line 10 (`p.name AS patient_name,`):** Selects the patient's name from table alias `p` (`patient`) and renames the column to `patient_name`.
* **Line 11 (`p.phone AS patient_phone`):** Selects the patient's phone number from table alias `p` and renames it to `patient_phone` (no trailing comma as it's the last selected field).
* **Line 12 (`FROM appointment a`):** Designates the `appointment` table as the base/primary table, giving it the shorthand alias `a`.
* **Line 13 (`JOIN doctor d ON a.doctor_id = d.id`):** Connects the `doctor` table (`d`) by matching the foreign key `a.doctor_id` to primary key `d.id`.
* **Line 14 (`JOIN patient p ON a.patient_id = p.id`):** Connects the `patient` table (`p`) by matching the foreign key `a.patient_id` to primary key `p.id`.
* **Line 15 (`WHERE DATE(a.appointment_time) = report_date`):** Extracts only the calendar date portion from `a.appointment_time` (stripping hours:minutes:seconds) and matches it against `report_date`.
* **Line 16 (`ORDER BY d.name, a.appointment_time;`):** Groups output alphabetically by doctor name, and sorts each doctor's appointments chronologically from morning to evening.
* **Line 17 (`END $$`):** Closes the procedure body (`}`) and uses `$$` to trigger compilation and storage.
* **Line 18 (`DELIMITER ;`):** Resets the statement delimiter back to standard semicolon `;`.

---

### 2. `GetDoctorWithMostPatientsByMonth`
* **Purpose:** Identifies the top-performing doctor who treated the highest number of patients in a specific month and year.
* **Input Parameters:** `IN input_month INT`, `IN input_year INT`

```sql
DELIMITER $$
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(
    IN input_month INT,
    IN input_year INT
)
BEGIN
    SELECT 
        doctor_id,
        COUNT(patient_id) AS patients_seen
    FROM appointment
    WHERE MONTH(appointment_time) = input_month
      AND YEAR(appointment_time) = input_year
    GROUP BY doctor_id
    ORDER BY patients_seen DESC
    LIMIT 1;
END $$
DELIMITER ;
```

---

### 3. `GetDoctorWithMostPatientsByYear`
* **Purpose:** Generates an annual executive summary identifying which doctor handled the maximum patient appointments across an entire calendar year.
* **Input Parameter:** `IN input_year INT`

```sql
DELIMITER $$
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(
    IN input_year INT
)
BEGIN
    SELECT 
        doctor_id,
        COUNT(patient_id) AS patients_seen
    FROM appointment
    WHERE YEAR(appointment_time) = input_year
    GROUP BY doctor_id
    ORDER BY patients_seen DESC
    LIMIT 1;
END $$
DELIMITER ;
```

---

## 💻 How to Run the Procedures (CLI Commands)

### Method 1: Using the Saved SQL Script File (Automated)
Run the script directly via PowerShell or Command Prompt:

```powershell
Get-Content "back-end\docs_bknd\M3\stored_procedures.sql" | & "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p1234 cms
```

### Method 2: Interactive MySQL CLI
```bash
mysql -u root -p
```
```sql
USE cms;

-- Paste the DELIMITER and CREATE PROCEDURE code blocks above
```

### Verifying Registered Procedures:
```sql
SHOW PROCEDURE STATUS WHERE Db = 'cms';
```

---

## 📋 Complete Assignment Execution Output (Submission Ready)

> [!IMPORTANT]
> The Coursera assignment states: *"For the assignment, you need to submit the complete output from each stored procedure execution. Save the terminal output."*
> Below is the verbatim terminal output from running all three procedures on the seeded `cms` database.

### 1. Daily Appointment Report Execution
```sql
CALL GetDailyAppointmentReportByDoctor('2025-04-15');
```

**Terminal Output:**
```text
+------------------+----------------------------+--------+----------------+---------------+
| doctor_name      | appointment_time           | status | patient_name   | patient_phone |
+------------------+----------------------------+--------+----------------+---------------+
| Dr. Ava Hall     | 2025-04-15 11:00:00.000000 |      1 | Lucas Turner   | 889-666-6666  |
| Dr. Mark Johnson | 2025-04-15 12:00:00.000000 |      1 | Michael Jordan | 888-444-4444  |
| Dr. Mark Johnson | 2025-04-15 13:00:00.000000 |      1 | Olivia Moon    | 888-555-5555  |
+------------------+----------------------------+--------+----------------+---------------+
3 rows in set (0.00 sec)
```

* **Interpretation:** On April 15, 2025, Dr. Ava Hall had 1 appointment (with Lucas Turner) and Dr. Mark Johnson had 2 consecutive appointments (with Michael Jordan and Olivia Moon). All statuses are `1` (Scheduled/Confirmed).

---

### 2. Doctor with Most Patients by Month Execution
```sql
CALL GetDoctorWithMostPatientsByMonth(4, 2025);
```

**Terminal Output:**
```text
+-----------+---------------+
| doctor_id | patients_seen |
+-----------+---------------+
|         2 |            31 |
+-----------+---------------+
1 row in set (0.00 sec)
```

* **Interpretation:** In Month 4 (April) of 2025, Doctor ID `2` (Dr. Mark Johnson, Neurologist) had the highest patient volume with **31 patients seen**.

---

### 3. Doctor with Most Patients by Year Execution
```sql
CALL GetDoctorWithMostPatientsByYear(2025);
```

**Terminal Output:**
```text
+-----------+---------------+
| doctor_id | patients_seen |
+-----------+---------------+
|         1 |            34 |
+-----------+---------------+
1 row in set (0.00 sec)
```

* **Interpretation:** In the entire year of 2025, Doctor ID `1` (Dr. Emily Adams, Cardiologist) had the highest overall patient volume across all doctors with **34 total patients seen**.

---

## 💡 Why Use Stored Procedures in Production?

1. **Pre-Compiled Performance:** MySQL compiles the execution plan once and caches it in memory. Subsequent executions run faster than ad-hoc SQL strings.
2. **Network Traffic Reduction:** Instead of sending 15-line SQL query strings across the network from Spring Boot to MySQL, the app transmits only `CALL GetDailyAppointmentReportByDoctor('2025-04-15')`.
3. **Database Security & Access Control:** Database administrators can grant users or microservices `EXECUTE` privileges on specific procedures without granting raw read/write table permissions.
4. **Centralized Business Rules:** If report logic or join conditions change, DBAs can update the procedure definition without recompiling and redeploying the Java Spring Boot service!

---
*Created as part of IBM Coursera Capstone Project — Module 3 deliverables.*
