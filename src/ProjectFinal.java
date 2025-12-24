import java.util.ArrayList;
import java.util.Scanner;

/**
 * كلاس ProjectFinal
 * 
 * ده النسخة الكونسول (Console) من المشروع.
 * بيشتغل كـ Demo عشان يجرب كلاسات الـ OOP من غير GUI أو داتابيز.
 * بيستخدم ArrayList لتخزين البيانات في الميموري مؤقتاً.
 */
public class ProjectFinal {

    static Scanner sc = new Scanner(System.in);
    static int FID;
    static int SID;
    static int AttendedDays;
    static int Section;
    static String pass;
    static String cname;
    
    public static void main(String[] args) {

        // تعريف القوائم لتخزين البيانات مؤقتاً
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Faculty> faculty = new ArrayList<>();
        ArrayList<AlertManager> alerts = new ArrayList<>();
        ArrayList<Course> course = new ArrayList<>();
        ArrayList<CourseAbsence> absences = new ArrayList<>();
        
        System.out.println("=== University System Demo ===");
        
        System.out.println("Choose Your Role");
        System.out.println("1. Faculty");
        System.out.println("2. Student");
        int role = getIntInRange(1,2);
        
        // إضافة بيانات تجريبية (Dummy Data)
        students.add(new Student(1, "123"));
        faculty.add(new Faculty(10, "admin"));
 
        switch(role){
            case 1 -> { // دور الدكتور (Faculty)
                System.out.println("Enter Faculty ID:");
                FID = getInt();
                
                System.out.println("Enter Faculty Password:");
                pass = getString();
                
                // البحث عن الدكتور
                Faculty currentFaculty = null;
                for (Faculty f : faculty) {
                    if (f.getFacutyId() == FID && f.getFacultyPassword().equals(pass)) {
                        currentFaculty = f;
                        break;
                    }
                }
                
                if (currentFaculty == null) {
                    System.out.println("Login Failed!");
                    return;
                }
                
                System.out.println("Login Successful! Welcome Faculty");
                
                System.out.println("Enter Student ID:");
                SID = getInt();
                
                // البحث عن الطالب لتعديل بياناته
                Student targetStudent = null;
                for (Student s : students) {
                    if (s.getStudentId() == SID) {
                        targetStudent = s;
                        break;
                    }
                }
                
                if (targetStudent == null) {
                    System.out.println("Student not found!");
                    return;
                }
                
                System.out.println("Enter Student Section:");
                Section = getInt();
                
                System.out.println("Enter number of courses:");
                int numCourses = getIntInRange(1, 10);
                
                double totalScore = 0;
                int totalHours = 0;
                
                // إدخال بيانات المواد
                for (int i = 0; i < numCourses; i++) {
                    System.out.println("Course Name:");
                    cname = getString();
                    
                    System.out.println("Course Score:");
                    double score = getDoubleInRange(0, 100);
                    
                    System.out.println("Credit Hours:");
                    int hours = getIntInRange(1, 3);
                    
                    course.add(new Course(SID, Section, cname, hours, score));
                    
                    totalScore += score * hours;
                    totalHours += hours;
                }
                
                // حساب وعرض الـ GPA
                double gpa = currentFaculty.Calculategpa(totalScore, totalHours);
                System.out.println("GPA Calculated = " + gpa);
                
                // إدخال بيانات الغياب
                System.out.println("Enter course name for absence:");
                cname = getString();
                
                System.out.println("Total lectures:");
                int totalLectures = getIntInRange(1, 100);
                
                System.out.println("Absent lectures:");
                int absentLectures = getIntInRange(0, totalLectures);
                
                absences.add(new CourseAbsence(SID, cname, totalLectures));
                absences.get(absences.size() - 1).absentLectures = absentLectures;
                
                
                System.out.println("Enter Attended Days For This Student:");
                AttendedDays = getInt();
                
                alerts.add(new AlertManager(SID, AttendedDays));
                
                System.out.println("Student data saved successfully");
            }
                
            case 2 -> { // دور الطالب (Student)
                System.out.println("Enter Student ID:");
                SID = getInt();
                
                System.out.println("Enter Student Password:");
                pass = getString();
                
                Student currentStudent = null;
                for (Student s : students) {
                    if (s.getStudentId() == SID && s.getPassword().equals(pass)) {
                        currentStudent = s;
                        break;
                    }
                }
                
                if (currentStudent == null) {
                    System.out.println("Login Failed!");
                    return;
                }
                
                System.out.println("Login Successful! Welcome Student");
                
                
                AlertManager alert = null;
                for (AlertManager al : alerts) {
                    if (al.getStudentId() == SID) {
                        alert = al;
                        break;
                    }
                }
                
                System.out.println("\n--- Attendance Days ---");
                if (alert != null)
                    System.out.println(alert.getAttendanceReport(AttendedDays));
                else
                    System.out.println("No attendance data.");
                
                System.out.println("\n--- Course Absence ---");
                boolean found = false;
                
                for (CourseAbsence ca : absences) {
                    if (ca.studentId == SID) {
                        found = true;
                        System.out.println(
                                "Course: " + ca.courseName +
                                        " | Total: " + ca.totalLectures +
                                        " | Absent: " + ca.absentLectures
                        );
                    }
                }
                
                if (!found)
                    System.out.println("No course absence data.");
                
                
                double sTotalScore = 0;
                int sTotalHours = 0;
                
                for (Course c : course) {
                    if (c.getStudentId() == SID) {
                        sTotalScore += c.getScore() * c.getCreditHours();
                        sTotalHours += c.getCreditHours();
                    }
                }
                
                if (sTotalHours > 0) {
                    Faculty f = faculty.get(0);
                    double sgpa = f.Calculategpa(sTotalScore, sTotalHours);
                    System.out.println("\n--- GPA ---");
                    System.out.println("Your GPA = " + sgpa);
                } else {
                    System.out.println("No courses found.");
                }   }

            default -> System.out.println("GoodBye");
            
        }
}
    // دوال مساعدة للإدخال (Helper Methods)
    public static int getInt() {
        while (true) {
            try {
                System.out.print("");
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
    public static int getIntInRange(int min, int max) {
        while (true) {
            int val = getInt();
            if (val >= min && val <= max) return val;
            System.out.println("Value must be between " + min + " and " + max);
        }
    }
    public static double getDoubleInRange(double min, double max) {
        while (true) {
            try {
                System.out.print("");
                double val = Double.parseDouble(sc.nextLine());
                if (val >= min && val <= max) return val;
                System.out.println("Value must be between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
    public static String getString() {
        while (true) {
            System.out.print("");
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Cannot be empty.");
            }
          }
        }
