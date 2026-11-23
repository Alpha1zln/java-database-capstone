package com.project.back_end.models;

// JPA annotation: Marks this class as an entity mapped to a MySQL database table
import jakarta.persistence.Entity;
// JPA annotation: Automatically generates unique primary keys (1, 2, 3...)
import jakarta.persistence.GeneratedValue;
// JPA enum: Uses database auto-increment identity column
import jakarta.persistence.GenerationType;
// JPA annotation: Identifies this field as the Primary Key
import jakarta.persistence.Id;
// JPA annotation: Specifies the foreign key column name in the database
import jakarta.persistence.JoinColumn;
// JPA annotation: Defines a Many-to-One relationship between entities
import jakarta.persistence.ManyToOne;
// JPA annotation: Explicitly names the MySQL table 'appointments'
import jakarta.persistence.Table;
// JPA annotation: Marks a method/field as NOT saved in the database (computed on the fly)
import jakarta.persistence.Transient;
// Bean Validation: Ensures the date/time is in the future (no past booking!)
import jakarta.validation.constraints.Future;
// Bean Validation: Ensures the field cannot be null
import jakarta.validation.constraints.NotNull;
// Java Time APIs: For handling dates, times, and timestamps
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

// 1. @Entity tells Spring Boot & Hibernate: "Map this Appointment class to a MySQL table"
@Entity
// 2. @Table sets the table name in MySQL to 'appointment'
@Table(name = "appointment")
public class Appointment {

    // 3. @Id marks this as the unique Primary Key for the appointment booking
    @Id
    // 4. @GeneratedValue auto-generates ID values (1, 2, 3...) on new record insert
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 5. @ManyToOne: Many appointments can belong to ONE Doctor
    // @JoinColumn creates foreign key column 'doctor_id' referencing doctors(id)
    @NotNull(message = "Doctor is required for an appointment")
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // 6. @ManyToOne: Many appointments can be booked by ONE Patient
    // @JoinColumn creates foreign key column 'patient_id' referencing patients(id)
    @NotNull(message = "Patient is required for an appointment")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // 7. Scheduled appointment start date and time
    // @Future prevents patients from booking slots in the past
    @NotNull(message = "Appointment time is required")
    @Future(message = "Appointment time must be in the future")
    private LocalDateTime appointmentTime;

    // 8. Appointment status:
    // 0 = Scheduled / Booked
    // 1 = Completed
    // 2 = Cancelled
    @NotNull(message = "Status is required")
    private int status;

    // 9. Default No-Argument Constructor: Required by JPA/Hibernate
    public Appointment() {
    }

    // 10. Parameterized Constructor without ID: For booking new appointments
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, int status) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    // 11. Parameterized Constructor with ID: For full object mapping and testing
    public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime appointmentTime, int status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    // 12. @Transient: Calculated on the fly (1 hour duration), NOT stored in MySQL
    @Transient
    public LocalDateTime getEndTime() {
        return appointmentTime != null ? appointmentTime.plusHours(1) : null;
    }

    // 13. @Transient helper: Extracts only the date (e.g. 2026-11-20) for easy UI display
    @Transient
    public LocalDate getAppointmentDate() {
        return appointmentTime != null ? appointmentTime.toLocalDate() : null;
    }

    // 14. @Transient helper: Extracts only the time (e.g. 10:00:00) for easy UI display
    @Transient
    public LocalTime getAppointmentTimeOnly() {
        return appointmentTime != null ? appointmentTime.toLocalTime() : null;
    }

    // 15. Getter for ID: Returns the appointment confirmation ID
    public Long getId() {
        return id;
    }

    // 16. Setter for ID: Allows setting the appointment ID
    public void setId(Long id) {
        this.id = id;
    }

    // 17. Getter for Doctor: Returns the assigned Doctor entity
    public Doctor getDoctor() {
        return doctor;
    }

    // 18. Setter for Doctor: Assigns a Doctor to this appointment
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    // 19. Getter for Patient: Returns the booked Patient entity
    public Patient getPatient() {
        return patient;
    }

    // 20. Setter for Patient: Assigns a Patient to this appointment
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // 21. Getter for AppointmentTime: Returns start date and time
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    // 22. Setter for AppointmentTime: Sets start date and time
    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    // 23. Getter for Status: Returns status integer (0=Scheduled, 1=Completed, 2=Cancelled)
    public int getStatus() {
        return status;
    }

    // 24. Setter for Status: Updates the appointment status
    public void setStatus(int status) {
        this.status = status;
    }
}
