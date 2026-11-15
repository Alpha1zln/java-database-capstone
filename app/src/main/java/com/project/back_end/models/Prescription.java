package com.project.back_end.models;

// Spring Data MongoDB annotation: Identifies the primary key (_id) in the MongoDB document
import org.springframework.data.annotation.Id;
// Spring Data MongoDB annotation: Maps this class to a collection named 'prescriptions' in MongoDB
import org.springframework.data.mongodb.core.mapping.Document;
// Bean Validation: Ensures the field cannot be null
import jakarta.validation.constraints.NotNull;
// Bean Validation: Enforces minimum and maximum character limits
import jakarta.validation.constraints.Size;

// 1. @Document tells Spring Boot & Spring Data Mongo: "Store this class as a JSON document in the 'prescriptions' collection"
@Document(collection = "prescriptions")
public class Prescription {

    // 2. @Id marks this as MongoDB's primary key (represented as a 24-character hexadecimal ObjectId string)
    @Id
    private String id;

    // 3. Name of the patient receiving the medication (between 3 and 100 characters)
    @NotNull(message = "Patient name is required")
    @Size(min = 3, max = 100, message = "Patient name must be between 3 and 100 characters")
    private String patientName;

    // 4. appointmentId: Links this MongoDB prescription back to the MySQL appointment booking
    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    // 5. Prescribed medication name (between 3 and 100 characters, e.g., Amoxicillin)
    @NotNull(message = "Medication name is required")
    @Size(min = 3, max = 100, message = "Medication name must be between 3 and 100 characters")
    private String medication;

    // 6. Dosage instructions (e.g., "500mg twice daily with meals")
    @NotNull(message = "Dosage information is required")
    private String dosage;

    // 7. Doctor's clinical advice or notes (optional, up to 200 characters)
    @Size(max = 200, message = "Doctor notes cannot exceed 200 characters")
    private String doctorNotes;

    // 8. Default No-Argument Constructor: Required by Spring Data MongoDB for deserialization
    public Prescription() {
    }

    // 9. Parameterized Constructor without ID: For issuing new prescriptions
    public Prescription(String patientName, Long appointmentId, String medication, String dosage, String doctorNotes) {
        this.patientName = patientName;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
        this.doctorNotes = doctorNotes;
    }

    // 10. Parameterized Constructor with ID: For full object mapping and testing
    public Prescription(String id, String patientName, Long appointmentId, String medication, String dosage, String doctorNotes) {
        this.id = id;
        this.patientName = patientName;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
        this.doctorNotes = doctorNotes;
    }

    // 11. Getter for ID: Returns MongoDB document ID
    public String getId() {
        return id;
    }

    // 12. Setter for ID: Allows setting MongoDB document ID
    public void setId(String id) {
        this.id = id;
    }

    // 13. Getter for PatientName: Returns the patient's name
    public String getPatientName() {
        return patientName;
    }

    // 14. Setter for PatientName: Sets or updates the patient's name
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    // 15. Getter for AppointmentId: Returns linked appointment ID
    public Long getAppointmentId() {
        return appointmentId;
    }

    // 16. Setter for AppointmentId: Links prescription to an appointment
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    // 17. Getter for Medication: Returns medication name
    public String getMedication() {
        return medication;
    }

    // 18. Setter for Medication: Sets or updates medication name
    public void setMedication(String medication) {
        this.medication = medication;
    }

    // 19. Getter for Dosage: Returns dosage instructions
    public String getDosage() {
        return dosage;
    }

    // 20. Setter for Dosage: Sets or updates dosage instructions
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    // 21. Getter for DoctorNotes: Returns doctor's special advice
    public String getDoctorNotes() {
        return doctorNotes;
    }

    // 22. Setter for DoctorNotes: Sets or updates doctor's special advice
    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }
}
