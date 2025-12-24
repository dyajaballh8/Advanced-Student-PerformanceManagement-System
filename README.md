University Performance Management System

Project Overview
The University Performance Management System is a professional academic tool designed to track student progress and simplify faculty administration. Built using Java 17 and Swing GUI, the software follows strict Object-Oriented Programming (OOP) principles like Inheritance and Encapsulation. It integrates with Microsoft SQL Server to ensure all academic data is stored securely and permanently.

Key Features
The application uses a Dual-Authentication system that identifies whether a user is a Student or Faculty member to provide the correct interface. The login screen features a high-quality campus image as a background with a semi-transparent layer to make the text easy to read.

Students can use a personalized dashboard to view their GPA, attendance rates through the AlertManager, and specific course grades in real-time. Faculty members have a dedicated control panel to manage student lists, update academic scores, and record attendance directly into the database.

Technical Stack and Architecture
The system is developed in Java using the Data Access Object (DAO) pattern to maintain clean communication with the database. The interface is built with Java Swing and AWT for a user-friendly desktop experience. The backend uses Microsoft SQL Server to handle complex data relationships and foreign keys. The architecture relies on Inheritance, where Student and Faculty classes inherit shared properties from a base User class.

Database Structure
The database consists of five main tables:

Users: Manages login details and access levels.

Students: Stores academic data like Major, Total Hours, and scores.

Faculty: Tracks instructor info and department oversight.

Attendance: Records attended days to trigger automatic warnings.

Courses: Acts as a catalog for course codes and credit hours.

Installation and Setup
First, initialize the database by running the Database.sql script in SQL Server Management Studio (SSMS) to create the StudentPerformanceDB and its tables. Next, place your campus image in the project root folder and name it background.jpg so the LoginFrame can display it. Finally, update the connection string in DBConnection.java to match your local server settings and run MainApp.java to start the program.
