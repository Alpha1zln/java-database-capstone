# Database Schema Design: Smart Clinic Management System

## MySQL Database Design

The relational database in MySQL manages core operational data that requires strict ACID transactions, data normalization, foreign key referential integrity, and concurrency controls to prevent scheduling collisions.

---

### Table: admin
Stores credentials and access control records for clinic administrators managing doctor profiles and clinic operations.

- `id`: BIGINT, Primary Key, Auto Increment
- `username`: VARCHAR(100), Not Null, Unique
- `password`: VARCHAR(255), Not Null *(Hashed with BCrypt)*

**Design Considerations & Constraints:**
- `username` has a unique constraint to ensure no duplicate administrative accounts.
- `password` length is set to 255 to store secure BCrypt password hashes.

---

### Table: doctors
Stores healthcare professional demographic, specialization, contact, and authentication details.

- `id`: BIGINT, Primary Key, Auto Increment
- `name`: VARCHAR(100), Not Null *(Validated between 3 and 100 characters)*
- `specialty`: VARCHAR(50), Not Null *(e.g., Cardiology, Dermatology, Pediatrics)*
- `email`: VARCHAR(100), Not Null, Unique *(Validated RFC 5322 format)*
- `password`: VARCHAR(255), Not Null *(Hashed with BCrypt)*
- `phone`: VARCHAR(10), Not Null *(Validated 10-digit numerical string)*

**Design Considerations & Constraints:**
- `email` is strictly unique to prevent duplicate doctor profiles and support username-based login.
- Contact numbers are validated through application-level Bean Validation regex (`^[0-9]{10}$`).

---

### Table: doctor_available_times
Represents the available daily consultation time slots for each doctor (maps to `@ElementCollection` in JPA).

- `id`: BIGINT, Primary Key, Auto Increment
- `doctor_id`: BIGINT, Foreign Key → `doctors(id)`, Not Null *(ON DELETE CASCADE)*
- `available_time`: VARCHAR(50), Not Null *(e.g., "09:00-10:00", "10:00-11:00")*

**Design Considerations & Constraints:**
- Foreign key relationship links each slot to a doctor. If a doctor is removed, their available schedule slots are automatically removed (`ON DELETE CASCADE`).
- Composite index on `(doctor_id, available_time)` ensures fast slot lookup when patients browse available hours.

---

### Table: patients
Stores patient demographic profiles, contact information, and authentication credentials.

- `id`: BIGINT, Primary Key, Auto Increment
- `name`: VARCHAR(100), Not Null *(Validated between 3 and 100 characters)*
- `email`: VARCHAR(100), Not Null, Unique *(Validated RFC 5322 format)*
- `password`: VARCHAR(255), Not Null *(Hashed with BCrypt)*
- `phone`: VARCHAR(10), Not Null *(Validated 10-digit numerical string)*
- `address`: VARCHAR(255), Not Null

**Design Considerations & Constraints:**
- `email` is unique across patients to ensure clean identity management and login.
- Passwords are encrypted before persisting to protect patient confidentiality and meet healthcare compliance standards.

---

### Table: appointments
Manages consultation bookings between patients and doctors, tracking scheduled times and lifecycle statuses.

- `id`: BIGINT, Primary Key, Auto Increment
- `doctor_id`: BIGINT, Foreign Key → `doctors(id)`, Not Null
- `patient_id`: BIGINT, Foreign Key → `patients(id)`, Not Null
- `appointment_time`: DATETIME, Not Null *(Must be in the future when booked)*
- `status`: INT, Not Null *(0 = Scheduled, 1 = Completed, 2 = Cancelled)*

**Design Considerations & Constraints:**
- **Double-Booking Prevention**: A unique constraint or transactional lock on `(doctor_id, appointment_time)` guarantees that a doctor cannot be scheduled for two concurrent appointments at the exact same hour.
- **Referential Integrity**: 
  - If a patient or doctor is archived or deactivated, appointments are preserved with a soft-delete status (`status = 2`) rather than hard-deleting the row, ensuring medical consultation audits remain complete.
- **Time Representation**: `appointment_time` stores both date and start time; consultation duration defaults to 1 hour (end time computed as `appointment_time + 1 HOUR`).

---

## MongoDB Collection Design

MongoDB handles flexible, semi-structured clinical records that frequently evolve and contain dynamic, nested data like multi-item medication lists and doctor instructions.

---

### Collection: prescriptions
Stores digital prescriptions issued by doctors during or after completed appointments. Prescriptions are linked to the relational MySQL appointment via `appointmentId`.

#### Example Document:
```json
{
  "_id": "64abc1234567890abcdef123",
  "appointmentId": 101,
  "patientName": "John Smith",
  "doctorId": 12,
  "medication": "Amoxicillin",
  "dosage": "500mg",
  "doctorNotes": "Take 1 tablet every 8 hours after food. Finish the full 5-day course.",
  "refillCount": 1,
  "issuedAt": "2026-11-14T10:30:00Z",
  "pharmacy": {
    "name": "HealthFirst Pharmacy",
    "location": "Market Street, Suite 400",
    "contact": "555-0199"
  },
  "medicationsList": [
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "1-0-1",
      "durationDays": 5,
      "instructions": "Take after meals"
    },
    {
      "name": "Paracetamol",
      "dosage": "650mg",
      "frequency": "SOS",
      "durationDays": 3,
      "instructions": "Only if fever exceeds 100°F"
    }
  ],
  "tags": ["Antibiotic", "Fever", "FollowUpRequired"]
}
```

#### Design Considerations & Flexibility:
1. **Hybrid Referencing vs Embedding**:
   - The document references `appointmentId` (from MySQL) rather than duplicating the entire patient or doctor profile. This keeps the document lightweight while maintaining a clear audit trail back to the relational appointment.
   - The `patientName` is captured directly in the document at issuance time to ensure historical immutability, even if the patient updates their account name later.
2. **Dynamic Medication Arrays**:
   - Unlike rigid relational schemas that require a separate `prescription_items` join table with rigid columns, MongoDB's document model allows variable lists of medications, custom dosage intervals, and arbitrary medical tags without schema migrations.
3. **Schema Evolution**:
   - Future additions—such as lab attachment URLs, pharmacy digital signatures, or patient allergies—can be seamlessly integrated into new documents without breaking backward compatibility.

---

### Collection: feedback

Complementing the core appointment and doctor records in MySQL, patient reviews and ratings are captured in MongoDB.

```json
{
  "_id": "ObjectId('67376a91f4b23c1290e11a9b')",
  "appointmentId": 101,
  "doctorId": 5,
  "patientId": 12,
  "patientName": "Alice Johnson",
  "rating": 5,
  "comment": "Dr. Smith was very attentive, explained the prescription clearly, and the clinic was spotless.",
  "tags": ["Punctual", "Polite Staff", "Clear Explanation"],
  "wouldRecommend": true,
  "submittedAt": "2026-11-15T22:30:00Z"
}
```

#### Why MongoDB for Feedback?
1. **Array Support for Tags**: Patients can attach 0 to many review tags (`tags: ["Punctual", "Clean Clinic"]`). In MongoDB, arrays are native fields rather than requiring complex join tables.
2. **Polyglot Bridge**: References MySQL entities (`appointmentId`, `doctorId`, `patientId`) for auditability, while decoupling survey changes from the transactional SQL schema.
3. **Analytics Performance**: Aggregations (average ratings per doctor, most common satisfaction tags) run rapidly in MongoDB without creating query load or locks on the operational MySQL appointments table.
