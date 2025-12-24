Here is a professional and structured README.md file for your project in English, tailored to your OOP structure, SQL Server database, and GUI design.

🎓 University Performance Management System
A comprehensive system designed to manage academic performance, built using Object-Oriented Programming (OOP) principles and a professional Swing GUI. The system is fully integrated with a Microsoft SQL Server database for persistent data management.

🚀 Key Features
Dual Authentication System: A smart login interface that distinguishes between Students and Faculty members based on database roles.

Immersive GUI: A professional Login screen featuring the campus as a background with a semi-transparent overlay for optimal readability.

Student Dashboard: Allows students to view their GPA, attendance rates, and individual course scores in real-time.

Faculty Control Panel: Enables professors to manage student lists, update academic scores, and monitor attendance records.

Persistent Storage: Robust database integration ensures all academic records are stored securely and retrieved efficiently.

🛠️ Tech Stack
Language: Java (JDK 17+).

GUI Framework: Java Swing.

Database: Microsoft SQL Server.

Architecture: OOP Patterns (Inheritance, Encapsulation, and Data Access Objects - DAO).

📋 Database Schema
The system architecture relies on the following relational tables:

Users: Stores credentials and account roles (Student/Faculty).

Students: Contains personal details and academic performance metrics.

Faculty: Stores lecturer information and department assignments.

Attendance: Records daily presence and absence logs.

Courses: Catalog of available subjects and their credit hours.

⚙️ Installation & Setup
Database Configuration:

Execute the Database.sql script in your SQL Server Management Studio (SSMS) to generate the tables and initial data.

Asset Setup:

Ensure the university image is saved as background.jpg in the project's root directory.

Connection String:

Update the database URL in the DBConnection.java class to match your local server instance.

Run Application:

Execute MainApp.java to launch the login window.
