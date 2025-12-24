
import java.util.Scanner;

public class Faculty extends User  {
    Scanner s =new Scanner(System.in);
    private int facutyId;
    private String name;
    private int section;
    private double score; 
    private double gpa;

    public Faculty(int facutyId,String password) {
        super(password);
        this.facutyId = facutyId;
    }
    public Faculty() {
    }

    public Faculty(int facutyId, String name, int section) {
        this.facutyId = facutyId;
        this.name = name;
        this.section = section;
        
    }
    
    public Faculty(int facutyId, String name, int section, int userID, String username, String password, String role) {
        super(userID, username, password, role);
        this.facutyId = userID;
        this.name =username;
        this.section = section;
        this.password=password;
        
    }

    public void setFacutyId(int facutyId) {
        this.facutyId = facutyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public void setFacultyPassword(String password) {
        this.password = password;
    }

    
    public int getFacutyId() {
        return facutyId;
    }

    public String getName() {
        return name;
    }

    public int getSection() {
        return section;
    }

    public String getFacultyPassword() {
        return password;
    }

    public int getFacultyID() {
        return facutyId;
    }
    
    
    public void enterGRade(int StudentId,int courseID,double score){
        System.out.println("Faculty Name : " + name +"Enter Grade : " + score +"For Student : " + StudentId + "In Course : " + courseID);
       
    }
    public void setAssessment(int courseID,String date,String type){
        System.out.println("Setting Assessment alert For course : " + courseID + "Type : " + type +"Date : "+ date);
    }
    public void ViewStudentPerformance(int StudentId){
        System.out.println("Viewing Performance For Student : " + StudentId);
        
    }
    public double Calculategpa(double score,  int hours )
    {
        double totalPoints = 0;
        int totalHours = 0;
            totalPoints += score * hours;
            totalHours += hours;
        
        double GPA = ((totalPoints / totalHours/100.0)*4.0);
         this.gpa=GPA ;
        return GPA;
    }
    public void setGpa(double GPA) {
        this.gpa = GPA;
    }
    public double getGpa() {
        return gpa;
    }
    
    @Override
    public void Login(int facutyId ,String password){
        this.password=password;
        this.facutyId=facutyId;
        System.out.println("Enter Your Password");
        password = s.next();
        System.out.println("Enter Your name");
        name = s.next();
        if(password==password && facutyId==facutyId){
            System.out.println("You Successfully Login");
        }
        else{
        System.out.println("Error , Enter Your Information Correctly");
        return;
         }    
    }
    @Override
        public void Logout(){
            System.out.println("You Successfully Logout");
        }
    @Override
    public void DisplayInfo(){
        System.out.println("College Name : " + name  +"College_ID : " + facutyId + "\nsection : " +section);
    }
}

