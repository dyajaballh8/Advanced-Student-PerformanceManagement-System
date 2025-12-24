# Comprehensive Project Integration Report

This document details every modification made to the project to achieve the integration of OOP, GUI, and Database components.

## 1. Project Restructuring

### **Moved OOP Classes**
**Action:** Copied all `.java` files from the `OOP` folder to the `GUI/Smartstudent/src` folder.
**Files:** `Student.java`, `Faculty.java`, `User.java`, `Course.java`, `Enrollment.java`, `CourseAbsence.java`.

### **Modified OOP Classes**
**Change:** Removed the package declaration `package ada.projectfinal;` from all moved files.
-   **Deleted:** Line 1 `package ada.projectfinal;`
-   **Why:** The GUI project uses the "default package" (no package declaration). For Java classes to see each other easily without complex import statements in this specific project structure, they must all belong to the same package (in this case, the default package).

---

## 2. New Components

### **Created `DatabaseConnection.java`**
**Description:** A helper class responsible for connecting to the SQL Server database.
**Content Added:**
-   `getConnection()` method.
-   Hardcoded connection URL: `jdbc:sqlserver://localhost:1433;databaseName=StudentPerformanceDB...`
-   `Class.forName(...)` to load the SQL Server JDBC driver.
-   **Why:** To centralize database connection logic. Instead of writing the connection code in every frame (Login, Student, Faculty), we write it once here and reuse it. This follows the "Don't Repeat Yourself" (DRY) principle.

---

## 3. Login Integration (`LoginFrame.java`)

### **Removed**
-   **Hardcoded Logic:** Removed the code that checked if the first letter of the ID was 'S' or 'D'.
    ```java
    // Deleted Code
    char firstChar = Character.toUpperCase(id.charAt(0));
    if (firstChar == 'S') { ... } 
    else if (firstChar == 'D') { ... }
    ```
-   **Why:** Hardcoded logic does not use the database. It allows anyone to login just by guessing a letter.

### **Added**
-   **Database Query:**
    ```java
    SELECT UserID, Role FROM Users WHERE Username = ? AND Password = ?
    ```
-   **Logic:**
    1.  Get input text from `userField` and `passField`.
    2.  Execute the SQL query.
    3.  If a row is returned (`rs.next()` is true), the user exists.
    4.  Check the `Role` column from the database.
    5.  If `Role` is "Student", open `StudentFrame(userId)`.
    6.  If `Role` is "Faculty", open `FacultyFrame()`.
-   **Why:** To ensure only registered users in the `Users` table can access the system, and to direct them to the correct screen based on their actual assigned role.

---

## 4. Student Interface Integration (`StudentFrame.java`)

### **Changed Constructor**
-   **Old:** `public StudentFrame() { ... }`
-   **New:** `public StudentFrame(int studentId) { ... }`
-   **Added:** stored `studentId` in a private field `currentStudentId`.
-   **Why:** The frame needs to know *which* student is logged in to show the correct grades. We pass the ID from the Login screen to the Student screen.

### **Added Data Loading Logic (`loadStudentData`)**
1.  **Personal Info Query:**
    -   `SELECT Name, Major, Score FROM Students WHERE StudentID = ?`
    -   Updates `namtext` (Name), `sectext` (Section/Major), and `gpatext` (GPA) labels.
2.  **Table Population:**
    -   `SELECT CourseName, TotalLectures, AbsentLectures FROM CourseAbsence ...`
    -   Clears the existing dummy table.
    -   Loops through results and adds real rows: `[Subject, 0, TotalHours, Absences]`.
-   **Why:** To replace the empty/static placeholders with real data from the database.

---

## 5. Faculty Interface Integration (`FacultyFrame.java`)

### **Added Save Logic (`savebuActionPerformed`)**
-   **Logic:**
    1.  Reads `Subject` (Course), `Student ID`, and `Score` from text fields.
    2.  Validates that fields are not empty.
    3.  Parses ID and Score to numbers.
    4.  Executes SQL Update:
        ```java
        UPDATE Students SET Score = ? WHERE StudentID = ?
        ```
-   **Why:** To give the "Save" button functionality. Previously, it did nothing. Now it permanently saves the grade to the database.

---

## Summary of Files Modified
1.  `Student.java`, `Faculty.java`, `User.java`, etc. (Package removal)
2.  `DatabaseConnection.java` (New file)
3.  `LoginFrame.java` (Logic replacement)
4.  `StudentFrame.java` (Data fetching)
5.  `FacultyFrame.java` (Data saving)
