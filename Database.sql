CREATE DATABASE StudentPerformanceDB;

CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Role NVARCHAR(20) NOT NULL
);

CREATE TABLE Students (
    StudentID INT PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Major NVARCHAR(50),
    Score FLOAT DEFAULT 0.0,
    TotalHours INT DEFAULT 0,
    EnrollmentsCount INT DEFAULT 0,
    CoursesCount INT DEFAULT 0,
    FacultyName NVARCHAR(100),
    FOREIGN KEY (StudentID) REFERENCES Users(UserID)
);

CREATE TABLE Faculty (
    FacultyID INT PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Section INT,
    GPA FLOAT DEFAULT 0.0,
    FOREIGN KEY (FacultyID) REFERENCES Users(UserID)
);

CREATE TABLE Courses (
    CourseID INT PRIMARY KEY,
    CourseCode NVARCHAR(20) NOT NULL,
    CourseName NVARCHAR(100) NOT NULL,
    CreditHours INT NOT NULL,
    Section INT
);

CREATE TABLE Attendance (
    StudentID INT PRIMARY KEY,
    TotalDays INT DEFAULT 80,
    AttendedDays INT DEFAULT 0,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
);

CREATE TABLE CourseAbsence (
    AbsenceID INT PRIMARY KEY IDENTITY(1,1),
    StudentID INT NOT NULL,
    CourseName NVARCHAR(100) NOT NULL,
    TotalLectures INT DEFAULT 0,
    AbsentLectures INT DEFAULT 0,
    Score FLOAT DEFAULT 0.0,
    CreditHours INT DEFAULT 3,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
);


INSERT INTO Users (UserID, Username, Password, Role) VALUES (1, 'ahmed_student', '123', 'Student');

INSERT INTO Users (UserID, Username, Password, Role) VALUES (2, 'student2', '123', 'Student');

INSERT INTO Users (UserID, Username, Password, Role) VALUES (3, 'student3', '123', 'Student');

INSERT INTO Users (UserID, Username, Password, Role) VALUES (4, 'student4', '123', 'Student');

INSERT INTO Users (UserID, Username, Password, Role) VALUES (5, 'student5', '123', 'Student');

INSERT INTO Users (UserID, Username, Password, Role) VALUES (2, 'student', '123', 'Student');

INSERT INTO Users (UserID, Username, Password, Role) VALUES (10, 'dr_admin', 'admin', 'Faculty');

INSERT INTO Users (UserID, Username, Password, Role) VALUES (11, 'Mohamed', 'Mohamed100', 'Faculty');


INSERT INTO Students (StudentID, Name, Major, Score, TotalHours, EnrollmentsCount, CoursesCount, FacultyName) 
VALUES (1, 'Ahmed Ali', 'Computer Science', 0.0, 15, 2, 5, 'Engineering');

INSERT INTO Students (StudentID, Name, Major, Score, TotalHours, EnrollmentsCount, CoursesCount, FacultyName) 
VALUES (3, 'Ahmed Ahmed', 'Computer Science', 0.0, 15, 2, 5, 'Engineering');

INSERT INTO Students (StudentID, Name, Major, Score, TotalHours, EnrollmentsCount, CoursesCount, FacultyName) 
VALUES (4, 'Saad Ali', 'Computer Science', 0.0, 15, 2, 5, 'Engineering');

INSERT INTO Students (StudentID, Name, Major, Score, TotalHours, EnrollmentsCount, CoursesCount, FacultyName) 
VALUES (5, 'Nour Ali', 'Computer Science', 0.0, 15, 2, 5, 'Engineering');

INSERT INTO Students (StudentID, Name, Major, Score, TotalHours, EnrollmentsCount, CoursesCount, FacultyName) 
VALUES (6, 'Mohamed Ali', 'Computer Science', 0.0, 15, 2, 5, 'Engineering');


INSERT INTO Faculty (FacultyID, Name, Section, GPA) 
VALUES (10, 'Dr. Admin', 1, 4.0);

INSERT INTO Attendance (StudentID, TotalDays, AttendedDays) 
VALUES (1, 80, 65);

INSERT INTO Attendance (StudentID, TotalDays, AttendedDays) 
VALUES (2, 70, 60);


INSERT INTO Courses (CourseID, CourseCode, CourseName, CreditHours, Section) 
VALUES (101, 'CS101', 'Java Programming', 3, 1);

INSERT INTO Courses (CourseID, CourseCode, CourseName, CreditHours, Section) 
VALUES (102, 'CS102', 'Python Programming', 3, 1);

INSERT INTO Courses (CourseID, CourseCode, CourseName, CreditHours, Section) 
VALUES (106, 'CS106', 'OOP', 3, 1);

INSERT INTO Courses (CourseID, CourseCode, CourseName, CreditHours, Section) 
VALUES (107, 'CS107', 'Math 3', 3, 1);

INSERT INTO Courses (CourseID, CourseCode, CourseName, CreditHours, Section) 
VALUES (108, 'CS108', 'Probabilaty', 3, 1);

INSERT INTO Courses (CourseID, CourseCode, CourseName, CreditHours, Section) 
VALUES (104, 'CS104', 'Database', 3, 4);

-- Initial Data for CourseAbsence (Testing)
INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (1, 'Java Programming', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (2, 'Python Programming', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (3, 'Database', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (1, 'Python Programming', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (1, 'Database', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (1, 'Math 3', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (1, 'Probabilaty', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (2, 'Python Programming', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (2, 'Database', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (2, 'Math 3', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (2, 'Probabilaty', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (3, 'Python Programming', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (3, 'Database', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (3, 'Math 3', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (3, 'Probabilaty', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (4, 'Python Programming', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (4, 'Database', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (4, 'Math 3', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (4, 'Probabilaty', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (5, 'Python Programming', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (5, 'Database', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (5, 'Math 3', 40, 2, 85.0, 3);

INSERT INTO CourseAbsence (StudentID, CourseName, TotalLectures, AbsentLectures, Score, CreditHours)
VALUES (5, 'Probabilaty', 40, 2, 85.0, 3);

select * from CourseAbsence
INSERT INTO Courses (CourseID, CourseCode, CourseName, CreditHours, Section) 
VALUES (120, 'CS105', 'Database', 3, 4);