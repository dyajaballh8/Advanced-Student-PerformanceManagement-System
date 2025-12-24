
import java.util.Scanner;

public class Student extends User {
    Scanner s = new Scanner(System.in);
    private int StudentId;
    private String name;
    private String major;
    private double score;
    private int courses;
    private int enroll;
    private String fac;
    private int hours;

    public Student(int StudentId, String password) {
        super(password);
        this.StudentId = StudentId;
    }

    
    
    public Student() {
       
    }
    public Student(int StudentId, String name,String password , String major, double score, int hours, int enroll, int courses ,String fac) {
        super(password);
        this.StudentId = StudentId;
        this.name = name;
        this.major = major;
        this.score = score;
        this.hours = hours;
        this.enroll = enroll;
        this.fac = fac;
        this.courses=courses;
    }
   
    public void setScore(double score) {
        this.score = score;
    }
    
    public void setStudentId(int StudentId) {
        this.StudentId = StudentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setEnroll(int enroll) {
        this.enroll = enroll;
    }

    public void setCourses(int courses) {
        this.courses = courses;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    
    
    
    public double getScore() {
        return score;
    }
    
    public int getStudentId() {
        return StudentId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getEnroll() {
        return enroll;
    }
    public String getMajor() {
        return major;
    }

    public String getFac() {
        return fac;
    }

    public int getCourses() {
        return courses;
    }

    public int getHours() {
        return hours;
    }
    public String ViewAttendance(){
        return "View Atendance For Steudent : " + StudentId;
    }
    
    @Override
    public void Login(int StudentId ,String password){
        this.password=password;
        this.StudentId=StudentId;
        System.out.println("Enter Your Password");
        password = s.next();
        System.out.println("Enter Your name");
        name = s.next();
        if(password.equals(password) && name.equals(StudentId)){
            System.out.println("You Successfully Login");
        }
        else{
        System.out.println("Error , Enter Your Information Correctly");
         }    
    }
    @Override
        public void Logout(){
            System.out.println("You Successfully Logout");
        }
    @Override
    public void DisplayInfo() {
        System.out.println("YourInformation Is : " + "\nStudentId : " + StudentId + "\nName : " + name + "\nMajor : " + major + "\nScore : " + score + "\nTotalHours : " + hours + "\nEnrollments : " + enroll + "\nCourses : " + courses + "\nFaculty : " + fac);
    }
 }
