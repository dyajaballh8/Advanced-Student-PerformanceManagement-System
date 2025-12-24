

public class CourseAbsence {
    int studentId;
    String courseName;
    int totalLectures;
    int absentLectures;

    public CourseAbsence(int studentId, String courseName, int totalLectures) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.totalLectures = totalLectures;
        this.absentLectures = 0;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalLectures() {
        return totalLectures;
    }

    public void setTotalLectures(int totalLectures) {
        this.totalLectures = totalLectures;
    }

    public int getAbsentLectures() {
        return absentLectures;
    }

    public void setAbsentLectures(int absentLectures) {
        this.absentLectures = absentLectures;
    }
    
}
