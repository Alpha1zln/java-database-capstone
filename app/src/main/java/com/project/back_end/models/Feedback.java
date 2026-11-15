package com.project.back_end.models;

// Spring Data MongoDB: Marks this field as the unique document identifier (_id)
import org.springframework.data.annotation.Id;
// Spring Data MongoDB: Tells Mongo to store this object as a document in the 'feedback' collection
import org.springframework.data.mongodb.core.mapping.Document;
// Bean Validation: Ensures the rating cannot be higher than 5 stars
import jakarta.validation.constraints.Max;
// Bean Validation: Ensures the rating cannot be lower than 1 star
import jakarta.validation.constraints.Min;
// Bean Validation: Ensures the field cannot be null or empty
import jakarta.validation.constraints.NotNull;
// Bean Validation: Prevents excessively long feedback comments
import jakarta.validation.constraints.Size;
// Java util: List collection to store tags (e.g. ["Punctual", "Friendly"])
import java.util.List;
// Java time: To record exactly when the patient submitted the review
import java.time.LocalDateTime;

// 1. @Document tells Spring Boot & MongoDB:
// "Store each instance of this class as a JSON document inside the 'feedback' collection (folder)"
@Document(collection = "feedback")
public class Feedback {

    // 2. @Id marks this as MongoDB's unique document ID (the 24-character hex ObjectId)
    @Id
    private String id;

    // 3. Links this review back to the MySQL appointment booking ID
    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    // 4. Links this review back to the MySQL doctor ID
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    // 5. Links this review back to the MySQL patient ID
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    // 6. Name of the patient submitting the review (for quick display without SQL joins)
    @NotNull(message = "Patient name is required")
    @Size(min = 3, max = 100, message = "Patient name must be between 3 and 100 characters")
    private String patientName;

    // 7. Star rating from 1 (poor) to 5 (excellent)
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1 star")
    @Max(value = 5, message = "Rating cannot exceed 5 stars")
    private int rating;

    // 8. Free-form text comment/review written by the patient (up to 500 characters)
    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;

    // 9. Quick review tags selected by the patient (e.g., ["Polite", "Clean Clinic", "Punctual"])
    // MongoDB stores arrays natively without requiring any extra relational mapping tables!
    private List<String> tags;

    // 10. Simple boolean flag: Would the patient recommend this doctor to others?
    private boolean wouldRecommend;

    // 11. Timestamp when the feedback was submitted
    private LocalDateTime createdAt;

    // 12. Default No-Argument Constructor: Required by Spring Data MongoDB to reconstruct objects from BSON
    public Feedback() {
        this.createdAt = LocalDateTime.now();
    }

    // 13. Parameterized Constructor without ID: For saving a new feedback submission
    public Feedback(Long appointmentId, Long doctorId, Long patientId, String patientName, 
                    int rating, String comment, List<String> tags, boolean wouldRecommend) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.rating = rating;
        this.comment = comment;
        this.tags = tags;
        this.wouldRecommend = wouldRecommend;
        this.createdAt = LocalDateTime.now();
    }

    // 14. Full Constructor with ID: For testing and repository mapping
    public Feedback(String id, Long appointmentId, Long doctorId, Long patientId, String patientName, 
                    int rating, String comment, List<String> tags, boolean wouldRecommend, LocalDateTime createdAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.rating = rating;
        this.comment = comment;
        this.tags = tags;
        this.wouldRecommend = wouldRecommend;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    // 15. Standard Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isWouldRecommend() {
        return wouldRecommend;
    }

    public void setWouldRecommend(boolean wouldRecommend) {
        this.wouldRecommend = wouldRecommend;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
