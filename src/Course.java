
import java.util.List;
import java.util.ArrayList;


public class Course {
    private int StudentId;
    private int courseID;
    private int section;
    private int creditHours;
    private double TotalScore;
    private String code;
    private String name;
    private List<Faculty> facultyMembers;
    private List<Enrollment> enrollments;

    public Course() {
    }

    public Course(int StudentId,int section, String name, int creditHours, double TotalScore) {
        this.StudentId=StudentId;
        this.creditHours = creditHours;
        this.name = name;
        this.TotalScore=TotalScore;
        this.section=section;
    }
    
    
    public Course(int courseID, int creditHours, String code, String name, List<Faculty> facultyMembers, List<Enrollment> enrollments) {
        this.courseID = courseID;
        this.creditHours = creditHours;
        this.code = code;
        this.name = name;
        this.facultyMembers = new ArrayList<>();
        this.enrollments = new ArrayList<>();
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFacultyMembers(List<Faculty> facultyMembers) {
        this.facultyMembers = facultyMembers;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int StudentId) {
        this.StudentId = StudentId;
    }

    public double getScore() {
        return TotalScore;
    }
    

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }
    

    public int getCreditHours() {
        return creditHours;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    
    public void addFaculty(Faculty faculty){
        this.facultyMembers.add(faculty);
    }
    public void addEnrollment(Enrollment enrollments){
        this.enrollments.add(enrollments);
    } 
   } 
