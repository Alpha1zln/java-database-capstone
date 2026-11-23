package com.project.back_end.models;

// JPA annotation: Marks this class as a database table entity for MySQL
import jakarta.persistence.Entity;
// JPA annotation: Automatically generates unique primary key values (1, 2, 3...)
import jakarta.persistence.GeneratedValue;
// JPA enum: Specifies the IDENTITY strategy (auto-increment in MySQL)
import jakarta.persistence.GenerationType;
// JPA annotation: Marks the field as the primary key of the table
import jakarta.persistence.Id;
// JPA annotation: Specifies the exact name of the MySQL table ("admin")
import jakarta.persistence.Table;
// Bean Validation: Ensures the field cannot be null or empty
import jakarta.validation.constraints.NotNull;
// Jackson annotation: Controls JSON serialization (keeps password secure and write-only)
import com.fasterxml.jackson.annotation.JsonProperty;

// 1. @Entity tells Spring Boot & Hibernate: "This class maps to a table in the database"
@Entity
// 2. @Table specifies that the database table name is 'admin'
@Table(name = "admin")
public class Admin {

    // 3. @Id marks this as the primary key column in the database table
    @Id
    // 4. @GeneratedValue with IDENTITY tells MySQL to auto-increment this ID automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 5. @NotNull validates that username is mandatory and cannot be null
    @NotNull(message = "Username is required")
    private String username;

    // 6. @NotNull validates that password is required
    @NotNull(message = "Password is required")
    // 7. WRITE_ONLY ensures the password is accepted during login/signup, but NEVER sent back to the browser in JSON
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 8. Default No-Argument Constructor: JPA/Hibernate requires an empty constructor to create objects
    public Admin() {
    }

    // 9. Parameterized Constructor: Allows creating an Admin object with username and password
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 10. Parameterized Constructor with ID: Useful for testing and mapping
    public Admin(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // 11. Getter for ID: Returns the admin's database ID
    public Long getId() {
        return id;
    }

    // 12. Setter for ID: Allows setting the admin's ID
    public void setId(Long id) {
        this.id = id;
    }

    // 13. Getter for Username: Returns the admin's username
    public String getUsername() {
        return username;
    }

    // 14. Setter for Username: Updates the admin's username
    public void setUsername(String username) {
        this.username = username;
    }

    // 15. Getter for Password: Used internally by Spring to check password during login
    public String getPassword() {
        return password;
    }

    // 16. Setter for Password: Sets the admin's password
    public void setPassword(String password) {
        this.password = password;
    }
}
