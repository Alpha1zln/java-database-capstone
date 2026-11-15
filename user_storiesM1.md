# User Story Template

**Title:**  
_As a [user role], I want [feature/goal], so that [reason]._

**Acceptance Criteria:**  
1. [Criteria 1]  
2. [Criteria 2]  
3. [Criteria 3]  

**Priority:** [High/Medium/Low]  
**Story Points:** [Estimated Effort in Points]  
**Notes:**  
- [Additional information or edge cases]

---

# Exercise 2: Admin User Stories

## Admin Story 1: Admin Login
**Title:**  
_As an admin, I want to log into the portal with my username and password, so that I can manage the platform securely._

**Acceptance Criteria:**  
1. Given valid admin credentials, when submitted, the system authenticates the user and grants access to the Admin Dashboard.  
2. Given invalid username or password, an error message "Invalid username or password" is displayed, and access is denied.  
3. The session or authentication token is securely issued to prevent unauthorized URL access.  

**Priority:** High  
**Story Points:** 3  
**Notes:**  
- Passwords must be encrypted using BCrypt. Direct URL navigation to `/admin/dashboard` without authentication must redirect to login.

---

## Admin Story 2: Admin Logout
**Title:**  
_As an admin, I want to log out of the portal, so that I can protect system access._

**Acceptance Criteria:**  
1. When the admin clicks the "Logout" button, the active session or JWT token is invalidated.  
2. The user is immediately redirected to the public login screen.  
3. Clicking the browser "Back" button after logout does not allow access to cached dashboard data.  

**Priority:** High  
**Story Points:** 2  
**Notes:**  
- Ensure client-side tokens are purged from storage on logout.

---

## Admin Story 3: Add Doctor
**Title:**  
_As an admin, I want to add new doctors to the portal, so that their services are made available to patients for booking._

**Acceptance Criteria:**  
1. The admin can open an "Add Doctor" form containing fields for name, specialization, email, phone, and consultation fee.  
2. All required fields are validated (e.g., valid email format, positive consultation fee).  
3. Upon successful submission, a new doctor record is saved to the database and appears in the doctors list.  

**Priority:** High  
**Story Points:** 5  
**Notes:**  
- Doctor email must be unique in the system.

---

## Admin Story 4: Delete Doctor Profile
**Title:**  
_As an admin, I want to delete a doctor's profile from the portal, so that inactive or departed doctors are removed from the clinic directory._

**Acceptance Criteria:**  
1. The admin can select a doctor from the list and initiate deletion with a confirmation prompt.  
2. Upon confirmation, the doctor profile is removed from the active directory.  
3. If the doctor has pending appointments, the system warns the admin or reassigns/cancels existing bookings before deletion.  

**Priority:** Medium  
**Story Points:** 3  
**Notes:**  
- Consider soft delete (active status flag) to preserve historical appointment records for audit purposes.

---

## Admin Story 5: Run Stored Procedure for Monthly Appointments
**Title:**  
_As an admin, I want to run a stored procedure in MySQL CLI to get the number of appointments per month, so that I can track clinic usage statistics and trends._

**Acceptance Criteria:**  
1. Executing the stored procedure `CALL sp_get_monthly_appointments()` in MySQL CLI executes without syntax or runtime errors.  
2. The output returns columns for year, month, and total appointment count.  
3. The query aggregates records correctly across all appointment statuses.  

**Priority:** Medium  
**Story Points:** 3  
**Notes:**  
- Stored procedure logic must be maintained in database migration/seed scripts.

---

# Exercise 3: Patient User Stories

## Patient Story 1: Browse Doctors without Login
**Title:**  
_As a patient, I want to view a list of doctors without logging in, so that I can explore consultation options before registering._

**Acceptance Criteria:**  
1. Unauthenticated visitors can view the public doctors directory page.  
2. Each doctor card displays the doctor's name, specialization, and consultation fee.  
3. Attempting to click "Book Appointment" prompts the patient to log in or register.  

**Priority:** High  
**Story Points:** 3  
**Notes:**  
- Public API endpoint for doctor listing must not expose sensitive personal doctor data.

---

## Patient Story 2: Patient Registration / Sign Up
**Title:**  
_As a patient, I want to sign up using my email and password, so that I can create an account to book appointments._

**Acceptance Criteria:**  
1. Registration form captures patient name, email, phone number, and password.  
2. Email format and password complexity (minimum length) are validated before submission.  
3. If the email is already registered, a clear error message is shown without duplicate record creation.  

**Priority:** High  
**Story Points:** 5  
**Notes:**  
- Passwords must be hashed with BCrypt before storage in MySQL.

---

## Patient Story 3: Patient Login
**Title:**  
_As a patient, I want to log into the portal, so that I can manage my bookings and medical records._

**Acceptance Criteria:**  
1. Given registered credentials, when submitted, the patient is authenticated and redirected to the Patient Dashboard.  
2. An authentication token or session is established for subsequent protected requests.  
3. Invalid login attempts return an error message without disclosing whether the email or password was wrong.  

**Priority:** High  
**Story Points:** 3  
**Notes:**  
- Rate-limiting should protect the login endpoint from brute-force attempts.

---

## Patient Story 4: Patient Logout
**Title:**  
_As a patient, I want to log out of the portal, so that I can secure my account when I am finished._

**Acceptance Criteria:**  
1. Clicking "Logout" terminates the current session and purges client-side authentication tokens.  
2. The browser redirects the user to the public landing page or login screen.  
3. Back-navigation to authenticated patient pages is blocked after logout.  

**Priority:** High  
**Story Points:** 2  
**Notes:**  
- Ensure no patient medical data remains cached in browser local storage.

---

## Patient Story 5: Book 1-Hour Appointment
**Title:**  
_As a patient, I want to log in and book an hour-long appointment to consult with a doctor, so that I can receive medical advice._

**Acceptance Criteria:**  
1. Authenticated patient selects a doctor, date, and available 1-hour time slot.  
2. System checks slot availability in real time and prevents double-booking of the same time slot.  
3. Upon booking confirmation, an appointment record is created with status "BOOKED" and linked to both patient and doctor.  

**Priority:** High  
**Story Points:** 5  
**Notes:**  
- Transactional locking must guarantee atomicity when multiple patients attempt to book the same slot simultaneously.

---

## Patient Story 6: View Upcoming Appointments
**Title:**  
_As a patient, I want to view my upcoming appointments, so that I can prepare accordingly and not miss my visits._

**Acceptance Criteria:**  
1. Patient Dashboard displays a list of all upcoming scheduled appointments.  
2. Each item displays doctor name, specialization, appointment date, time, and status.  
3. Only appointments belonging to the logged-in patient are visible (strict data isolation).  

**Priority:** Medium  
**Story Points:** 3  
**Notes:**  
- Appointments should be sorted chronologically by date and time.

---

# Exercise 4: Doctor User Stories

## Doctor Story 1: Doctor Login
**Title:**  
_As a doctor, I want to log into the portal, so that I can manage my appointments and patient consultations._

**Acceptance Criteria:**  
1. Doctor enters registered credentials and, upon validation, is redirected to the Doctor Dashboard.  
2. System verifies doctor role permissions, restricting access to doctor-specific workflows.  
3. Invalid credentials display an appropriate error notification.  

**Priority:** High  
**Story Points:** 3  
**Notes:**  
- Doctor accounts are provisioned by the clinic administrator.

---

## Doctor Story 2: Doctor Logout
**Title:**  
_As a doctor, I want to log out of the portal, so that I can protect patient and clinical data._

**Acceptance Criteria:**  
1. Clicking "Logout" revokes active tokens and clears clinical consultation state from the client.  
2. User is redirected to the main login portal.  
3. Unauthenticated requests to doctor endpoints immediately return 401 Unauthorized or 403 Forbidden.  

**Priority:** High  
**Story Points:** 2  
**Notes:**  
- Essential for shared clinic workstation security.

---

## Doctor Story 3: View Appointment Calendar
**Title:**  
_As a doctor, I want to view my appointment calendar, so that I can stay organized and manage my daily consultation queue._

**Acceptance Criteria:**  
1. Doctor Dashboard displays a daily/weekly calendar view of scheduled appointments.  
2. Appointments show patient name, booked time slot, and consultation status.  
3. Doctor can filter or switch views between today's appointments and upcoming dates.  

**Priority:** High  
**Story Points:** 5  
**Notes:**  
- Calendar view should highlight completed vs pending appointments clearly.

---

## Doctor Story 4: Mark Unavailability / Manage Slots
**Title:**  
_As a doctor, I want to mark my unavailability, so that patients only see and book open, available slots._

**Acceptance Criteria:**  
1. Doctor can select dates or specific time blocks to mark as "Unavailable" or "Leave".  
2. Marked unavailable slots are instantly removed from the patient booking calendar.  
3. System prevents patients from selecting any slot that falls within an unavailable block.  

**Priority:** High  
**Story Points:** 5  
**Notes:**  
- If existing appointments fall on newly marked leave dates, the system must flag them for rescheduling.

---

## Doctor Story 5: Update Doctor Profile
**Title:**  
_As a doctor, I want to update my profile with specialization and contact information, so that patients have up-to-date information._

**Acceptance Criteria:**  
1. Doctor can navigate to "Profile Settings" and edit contact number, specialization, and bio.  
2. Input validation ensures phone number and email follow valid formats.  
3. Changes persist to MySQL and update immediately on the public doctor directory.  

**Priority:** Medium  
**Story Points:** 3  
**Notes:**  
- Specialization updates should reflect in search and filter dropdowns.

---


## Doctor Story 6: View Patient Details for Upcoming Appointments
**Title:**  
_As a doctor, I want to view the patient details for upcoming appointments, so that I can be prepared for the consultation._

**Acceptance Criteria:**  
1. Doctor can click on any scheduled appointment to view the patient's name, age, gender, contact number, and reason for visit.  
2. The view displays previous consultation history and prescriptions linked to this patient.  
3. Access is strictly authorized—doctors can only view details for patients scheduled with them.  

**Priority:** High  
**Story Points:** 5  
**Notes:**  
- Prescriptions are fetched from MongoDB, while patient profile data is retrieved from MySQL.



---
*************   
*************   
---



# Who actually writes them?
Product Managers (PM) or Product Owners (PO) write the business requirements and user stories.
UI/UX Designers attach the Figma design links to the ticket.

## What you (the Developer) do:
Sprint Grooming / Backlog Refinement (once a week): The PM shows you the story. You ask technical questions like:
"What happens if the database connection times out?"
"Do we need to invalidate previous tokens on password reset?"

### Estimation (Story Points): The dev team votes on effort (e.g., 1, 2, 3, 5, or 8 points) using Planning Poker.

## Create Technical Subtasks: As a developer, you break their story down into engineering tasks:
- Task 1: Create DoctorRepository and MySQL migration script (1 point)
- Task 2: Implement /api/v1/doctors REST controller (2 points)
- Task 3: Write JUnit 5 & Mockito tests (1 point)

---------

## How modern tech companies build software.

### Here is the exact lifecycle of how an idea turns into production code in an Agile company:

*🔄 The Real-World Agile Lifecycle
```
[1. Client / Users] 
       │ (Feedback, business needs, complaints)
       ▼
[2. Product Manager (PM) / Product Owner (PO)]
       │ • Gathers requirements & writes User Stories in Jira
       ▼
[3. UI / UX Design Team]
       │ • Creates wireframes & interactive prototypes in Figma
       ▼
[4. Sprint Planning & Refinement Meeting]
       │ • PM + Dev Team discuss the stories & Figma screens
       │ • Devs challenge edge cases & estimate Story Points (1, 2, 3, 5, 8)
       │ • Devs break the story into technical subtasks
       ▼
[5. 2-Week Sprint Execution (Developers)]
       │ • Devs pick subtasks from the Jira board (To Do ➔ In Progress)
       │ • Write code + Unit Tests (JUnit/Mockito)
       │ • Daily Standup (15-min check-in: Yesterday, Today, Blockers)
       │ • Raise a Pull Request (PR) on GitHub
       │ • Peer Code Review + CI/CD automated tests
       ▼
[6. QA / Testing & Deployment]
       │ • QA tests on Staging server
       │ • Merge to `main` & Deploy to Production via Docker/CI-CD
```

## 💬 What a Developer Does Day-to-Day in a Sprint:
- Morning (Daily Standup - 15 mins):  
    You quickly answer 3 things:  
    "Yesterday I finished the doctor repository and DB migration."  
    "Today I'm building the appointment booking REST endpoint."  
    "No blockers."  

- Daytime (Coding & Subtasks):  
    You move your Jira subtask from "In Progress" to "In Review".  
    You push your Git branch and open a Pull Request (PR).  
    Another engineer reviews your code, leaves comments, and approves it.  
    - Automated CI/CD:  
        GitHub Actions runs tests automatically (mvn test).   
        When merged, Docker builds the container and pushes it to staging.   

### 🎯 work in an Agile environment

You can answer with total confidence:

"Yes, we worked in two-week Agile sprints. Our PM created user stories with clear acceptance criteria based on client requirements, while UX provided the Figma mocks. During sprint refinement, the engineering team estimated story points and decomposed stories into backend and database subtasks. We tracked daily progress in standups, used GitHub PRs for code reviews, and relied on CI/CD pipelines to run automated tests before deploying."



--------
---------