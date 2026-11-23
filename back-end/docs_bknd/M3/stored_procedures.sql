-- =====================================================================
-- SMART CLINIC MANAGEMENT SYSTEM (CMS) - STORED PROCEDURES
-- Module 3, Unit 2, Chapter 3: Adding Stored Procedures
-- Database: cms (MySQL)
-- =====================================================================

-- Switch to the clinic management system database
USE cms;

-- Clean up: Drop any older versions of these procedures before recreating them
DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear;

-- =====================================================================
-- 1. GetDailyAppointmentReportByDoctor
-- Purpose: Generates a daily schedule for clinic doctors on a specific date.
-- =====================================================================

-- Temporarily change statement delimiter from semicolon (;) to ($$)
-- This prevents MySQL from prematurely executing internal queries that end in (;)
DELIMITER $$

-- Command MySQL to register a new stored routine with the given name
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(
    -- Declare an incoming read-only parameter named 'report_date' of SQL type DATE ('YYYY-MM-DD')
    IN report_date DATE
-- Close the parameter declaration list
)
-- Mark the start of the procedure's executable body (equivalent to '{' in Java)
BEGIN
    -- Specify the output columns to fetch and return in the report
    SELECT 
        -- Fetch the doctor's name from table 'd' and rename column header to 'doctor_name' to avoid naming conflicts
        d.name AS doctor_name,
        -- Fetch the scheduled date and timestamp of the appointment from table 'a'
        a.appointment_time,
        -- Fetch the appointment status code (1 = Confirmed/Scheduled, 0 = Cancelled) from table 'a'
        a.status,
        -- Fetch the patient's full name from table 'p' and rename column header to 'patient_name'
        p.name AS patient_name,
        -- Fetch the patient's contact telephone number from table 'p' and rename to 'patient_phone'
        p.phone AS patient_phone
    -- Designate 'appointment' as the central base table and assign it the shorthand alias 'a'
    FROM appointment a
    -- Join doctor table (alias 'd'): match foreign key a.doctor_id with primary key d.id
    JOIN doctor d ON a.doctor_id = d.id
    -- Join patient table (alias 'p'): match foreign key a.patient_id with primary key p.id
    JOIN patient p ON a.patient_id = p.id
    -- Filter rows: extract only the calendar date part ('YYYY-MM-DD') from datetime and match with input parameter
    WHERE DATE(a.appointment_time) = report_date
    -- Primary sort by doctor name (groups doctors), secondary sort chronologically by appointment time
    ORDER BY d.name, a.appointment_time;
-- Close the procedure block ('}') and trigger compilation with the custom delimiter ($$)
END $$

-- Reset the statement delimiter back to standard semicolon (;) for subsequent SQL commands
DELIMITER ;


-- =====================================================================
-- 2. GetDoctorWithMostPatientsByMonth
-- Purpose: Identifies the doctor who saw the most patients in a given month and year.
-- =====================================================================

-- Temporarily change statement delimiter from (;) to ($$)
DELIMITER $$

-- Command MySQL to register the procedure
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(
    -- Input parameter for the target month as integer (1 to 12)
    IN input_month INT,
    -- Input parameter for the target calendar year as integer (e.g., 2025)
    IN input_year INT
-- Close parameter list
)
-- Mark the start of the executable body
BEGIN
    -- Select the doctor ID and the total count of patients seen
    SELECT 
        -- Doctor's unique identifier from the appointment table
        doctor_id,
        -- Aggregate function: count the number of patient visits for each doctor
        COUNT(patient_id) AS patients_seen
    -- Query directly from the appointments table
    FROM appointment
    -- Filter records matching the specified month and year using MONTH() and YEAR() date extraction functions
    WHERE MONTH(appointment_time) = input_month
      AND YEAR(appointment_time) = input_year
    -- Group appointments by each doctor so COUNT() calculates totals per doctor
    GROUP BY doctor_id
    -- Order results in descending order (highest patient count first)
    ORDER BY patients_seen DESC
    -- Restrict output to only the top 1 row (the doctor with the most patients)
    LIMIT 1;
-- End the procedure body and compile with custom delimiter ($$)
END $$

-- Reset delimiter back to standard semicolon (;)
DELIMITER ;


-- =====================================================================
-- 3. GetDoctorWithMostPatientsByYear
-- Purpose: Identifies the doctor who saw the most patients across an entire calendar year.
-- =====================================================================

-- Temporarily change statement delimiter from (;) to ($$)
DELIMITER $$

-- Command MySQL to register the procedure
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(
    -- Input parameter for the target calendar year as integer (e.g., 2025)
    IN input_year INT
-- Close parameter list
)
-- Mark the start of the executable body
BEGIN
    -- Select the doctor ID and total count of patients seen in the year
    SELECT 
        -- Doctor's unique identifier from the appointment table
        doctor_id,
        -- Aggregate function: count the total patient visits for each doctor in the year
        COUNT(patient_id) AS patients_seen
    -- Query directly from the appointments table
    FROM appointment
    -- Filter records matching the specified year using the YEAR() date function
    WHERE YEAR(appointment_time) = input_year
    -- Group appointments by each doctor so COUNT() calculates totals per doctor
    GROUP BY doctor_id
    -- Order results in descending order (highest patient count first)
    ORDER BY patients_seen DESC
    -- Restrict output to only the top 1 row (the overall busiest doctor of the year)
    LIMIT 1;
-- End the procedure body and compile with custom delimiter ($$)
END $$

-- Reset delimiter back to standard semicolon (;)
DELIMITER ;
