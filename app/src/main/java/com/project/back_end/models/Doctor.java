package com.project.back_end.models;

// JPA annotation: Tells JPA to store this list of elements (strings) in a separate helper table
import jakarta.persistence.ElementCollection;
// JPA annotation: Marks this class as an entity mapped to a MySQL database table
import jakarta.persistence.Entity;
// JPA annotation: Auto-generates unique primary keys (1, 2, 3...)
import jakarta.persistence.GeneratedValue;
// JPA enum: Uses database auto-increment identity column
import jakarta.persistence.GenerationType;
// JPA annotation: Identifies this field as the Primary Key
import jakarta.persistence.Id;
// JPA annotation: Explicitly names the MySQL table 'doctors'
import jakarta.persistence.Table;
// Bean Validation: Validates that the email follows a standard email format (name@example.com)
import jakarta.validation.constraints.Email;
// Bean Validation: Ensures the field cannot be null
import jakarta.validation.constraints.NotNull;
// Bean Validation: Validates phone number using regular expressions (exactly 10 digits)
import jakarta.validation.constraints.Pattern;
// Bean Validation: Enforces minimum and maximum character limits
import jakarta.validation.constraints.Size;
// Jackson annotation: Ensures password is write-only (hidden from frontend JSON API responses)
import com.fasterxml.jackson.annotation.JsonProperty;
// Java util: List collection to store multiple time slot strings
import java.util.List;

// 1. @Entity tells Spring Boot & Hibernate: "Create and map a table in MySQL for this Doctor class"
@Entity
// 2. @Table sets the table name in MySQL to 'doctors'
@Table(name = "doctors")
public class Doctor {

    // 3. @Id marks this as the unique Primary Key for the doctor
    @Id
    // 4. @GeneratedValue auto-generates ID values (1, 2, 3...) on new record insert
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 5. Doctor's name must be provided and between 3 and 100 characters
    @NotNull(message = "Doctor name is required")
    @Size(min = 3, max = 100, message = "Doctor name must be between 3 and 100 characters")
    private String name;

    // 6. Doctor's medical specialization (e.g., Cardiology) between 3 and 50 characters
    @NotNull(message = "Specialty is required")
    @Size(min = 3, max = 50, message = "Specialty must be between 3 and 50 characters")
    private String specialty;

    // 7. Doctor's email address, must be a valid email format
    @NotNull(message = "Email is required")
    @Email(message = "Email must be a valid email address format")
    private String email;

    // 8. Doctor's login password; minimum 6 characters; never leaked to frontend JSON responses
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 9. Doctor's phone number, validated to be exactly 10 digits
    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    // 10. List of available working time slots (e.g., ["09:00-10:00", "10:00-11:00"])
    // @ElementCollection stores these time slots in a linked helper table
    @ElementCollection
    private List<String> availableTimes;

    // 11. Default No-Argument Constructor: Required by JPA/Hibernate
    public Doctor() {
    }

    // 12. Parameterized Constructor: For creating a Doctor with all details
    public Doctor(String name, String specialty, String email, String password, String phone, List<String> availableTimes) {
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.availableTimes = availableTimes;
    }

    // 13. Parameterized Constructor with ID: For full object mapping and testing
    public Doctor(Long id, String name, String specialty, String email, String password, String phone, List<String> availableTimes) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.availableTimes = availableTimes;
    }

    // 14. Getter for ID: Returns the doctor's database ID
    public Long getId() {
        return id;
    }

    // 15. Setter for ID: Allows setting the doctor's ID
    public void setId(Long id) {
        this.id = id;
    }

    // 16. Getter for Name: Returns the doctor's name
    public String getName() {
        return name;
    }

    // 17. Setter for Name: Sets or updates the doctor's name
    public void setName(String name) {
        this.name = name;
    }

    // 18. Getter for Specialty: Returns the doctor's medical specialization
    public String getSpecialty() {
        return specialty;
    }

    // 19. Setter for Specialty: Sets or updates the doctor's specialization
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // 20. Getter for Email: Returns the doctor's email
    public String getEmail() {
        return email;
    }

    // 21. Setter for Email: Sets or updates the doctor's email
    public void setEmail(String email) {
        this.email = email;
    }

    // 22. Getter for Password: Used internally by Spring to verify credentials
    public String getPassword() {
        return password;
    }

    // 23. Setter for Password: Sets the doctor's password
    public void setPassword(String password) {
        this.password = password;
    }

    // 24. Getter for Phone: Returns the doctor's 10-digit phone number
    public String getPhone() {
        return phone;
    }

    // 25. Setter for Phone: Sets the doctor's phone number
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 26. Getter for AvailableTimes: Returns the list of time slots
    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    // 27. Setter for AvailableTimes: Sets or updates the doctor's available time slots
    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }
}
