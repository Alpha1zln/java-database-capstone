package com.project.back_end.models;

// JPA annotation: Marks this class as an entity mapped to a MySQL database table
import jakarta.persistence.Entity;
// JPA annotation: Automatically generates unique primary keys (1, 2, 3...)
import jakarta.persistence.GeneratedValue;
// JPA enum: Uses database auto-increment identity column
import jakarta.persistence.GenerationType;
// JPA annotation: Identifies this field as the Primary Key
import jakarta.persistence.Id;
// JPA annotation: Explicitly names the MySQL table 'patients'
import jakarta.persistence.Table;
// Bean Validation: Validates email format (e.g., patient@example.com)
import jakarta.validation.constraints.Email;
// Bean Validation: Ensures the field cannot be null
import jakarta.validation.constraints.NotNull;
// Bean Validation: Validates phone number to match exactly 10 digits
import jakarta.validation.constraints.Pattern;
// Bean Validation: Enforces minimum and maximum character limits
import jakarta.validation.constraints.Size;
// Jackson annotation: Ensures password is write-only (hidden from frontend JSON API responses)
import com.fasterxml.jackson.annotation.JsonProperty;

// 1. @Entity tells Spring Boot & Hibernate: "Create and map a table in MySQL for this Patient class"
@Entity
// 2. @Table sets the table name in MySQL to 'patient'
@Table(name = "patient")
public class Patient {

    // 3. @Id marks this as the unique Primary Key for the patient
    @Id
    // 4. @GeneratedValue auto-generates ID values (1, 2, 3...) on new record insert
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 5. Patient's full name, required and between 3 and 100 characters
    @NotNull(message = "Patient name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    // 6. Patient's email address, required and must be valid format
    @NotNull(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    // 7. Patient's password; minimum 6 characters; never leaked to frontend JSON responses
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 8. Patient's phone number, validated to be exactly 10 digits
    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    // 9. Patient's residential address, cannot exceed 255 characters
    @NotNull(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    // 10. Default No-Argument Constructor: Required by JPA/Hibernate
    public Patient() {
    }

    // 11. Parameterized Constructor: For creating a Patient with all details
    public Patient(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // 12. Parameterized Constructor with ID: For full object mapping and testing
    public Patient(Long id, String name, String email, String password, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // 13. Getter for ID: Returns the patient's database ID
    public Long getId() {
        return id;
    }

    // 14. Setter for ID: Allows setting the patient's ID
    public void setId(Long id) {
        this.id = id;
    }

    // 15. Getter for Name: Returns the patient's full name
    public String getName() {
        return name;
    }

    // 16. Setter for Name: Sets or updates the patient's name
    public void setName(String name) {
        this.name = name;
    }

    // 17. Getter for Email: Returns the patient's email address
    public String getEmail() {
        return email;
    }

    // 18. Setter for Email: Sets or updates the patient's email
    public void setEmail(String email) {
        this.email = email;
    }

    // 19. Getter for Password: Used internally by Spring for authentication
    public String getPassword() {
        return password;
    }

    // 20. Setter for Password: Sets the patient's password
    public void setPassword(String password) {
        this.password = password;
    }

    // 21. Getter for Phone: Returns the patient's 10-digit phone number
    public String getPhone() {
        return phone;
    }

    // 22. Setter for Phone: Sets the patient's phone number
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 23. Getter for Address: Returns the patient's home address
    public String getAddress() {
        return address;
    }

    // 24. Setter for Address: Sets or updates the patient's home address
    public void setAddress(String address) {
        this.address = address;
    }
}
