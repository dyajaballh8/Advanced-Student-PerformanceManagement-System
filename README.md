🎓 University Performance Management System
📝 Overview
The University Performance Management System is a sophisticated academic solution developed to streamline the tracking of student progress and faculty administrative tasks. Built with Java 17 and Swing GUI, the system adheres to strict Object-Oriented Programming (OOP) principles such as Inheritance and Encapsulation. It features a robust integration with Microsoft SQL Server to ensure data persistence and security.

🚀 Key System Features
Security & Authentication The system implements a Dual-Authentication Mechanism that dynamically routes users based on their assigned roles in the database. Upon launching, users are greeted by an Immersive GUI featuring a high-quality campus background with a semi-transparent overlay, ensuring a professional look and high text readability.

Student Experience Students have access to a personalized Student Dashboard where they can monitor their academic standing in real-time. This includes live tracking of their GPA, attendance percentages via the AlertManager system, and a detailed breakdown of scores for each enrolled course.

Faculty Management For educators, the Faculty Control Panel provides a comprehensive suite for class administration. Faculty members can manage student registries, update academic scores, and log attendance directly into the Students and Attendance tables.

🛠️ Technical Stack
Core Language: Developed using Java (JDK 17+) utilizing the DAO (Data Access Object) pattern for clean database interaction.

User Interface: Crafted with Java Swing and AWT to provide a responsive and user-friendly desktop experience.

Database Engine: Powered by Microsoft SQL Server for managing complex relational data and foreign key constraints.

Architecture: Heavily relies on Inheritance (e.g., Student and Faculty inheriting from User) and Encapsulation to protect sensitive data fields.

📋 Database Structure
The backend is organized into five primary relational tables designed for maximum efficiency:

Users Table: Manages login credentials and role-based access control.

Students Table: Stores detailed academic metrics including TotalHours, Major, and cumulative scores.

Faculty Table: Tracks instructor data, including department sections and GPA oversight.

Attendance Table: Logs total days vs. attended days for automated warning triggers.

Courses Table: Serves as a central catalog for subject codes and credit hours.

⚙️ Installation & Setup
Step 1: Database Initialization Open SQL Server Management Studio (SSMS) and execute the provided Database.sql script. This will create the StudentPerformanceDB and populate it with the necessary schema and initial test data.

Step 2: Asset Management Place the university campus image in the project's root directory and rename it to background.jpg. The LoginFrame class is specifically coded to look for this filename to render the background.

Step 3: Configuration & Launch Update the connection string in the DBConnection.java file to match your local SQL Server instance (Server Name, Port, and Integrated Security settings). Finally, run the MainApp.java file to launch the application.
