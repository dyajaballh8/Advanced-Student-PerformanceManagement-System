


public class Enrollment implements PerfomanceTracker {
   private int enrollmentID;
   private String semester;
   private String Type;
   private double Score;
   private int year;
   private Student student;
   private Course course;

    public Enrollment() {
    }

    public Enrollment(int enrollmentID, String semester, String Type, int year) {
        this.enrollmentID = enrollmentID;
        this.semester = semester;
        this.Type = Type;
        this.year = year;
    }

    public Enrollment(int enrollmentID, String semester, String Type, int year, Student student, Course course,double Score ) {
        this.enrollmentID = enrollmentID;
        this.semester = semester;
        this.Type = Type;
        this.year = year;
        this.Score=Score;
        this.student = student;
        this.course = course;
        course.addEnrollment(this);
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }
    public double getScore() {
        return Score;
    }
    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public void addGrade(String Type,double Score){
        System.out.println("Adding Grade "+ Type + "With Score " + Score );
        updateRate(calculateRate(Score));
    }
    public void updateRate(double rate){
        System.out.println("Updating Performance  Rate to : " + rate);
        
    }
   @Override
    public double calculateRate(double score){
        return (score/100.0)*4; 
    }
   @Override
    public String getPerfomanceTrend(int StudendID){
        return"Trend Data for Student  " + StudendID ;
    }
}

