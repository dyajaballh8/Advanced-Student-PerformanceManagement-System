

public abstract class User {
    protected int userID;
    private String username;
    protected String password;
    private String role;

    public User() {
    }

    public User(String password) {
        this.password = password;
    }
    

    public User(int userID, String username, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.password =password ;
        this.role = role;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password ="**********";
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }
    
    public String getRole() {
        return role;
    }
    
    public abstract void Login(int userID ,String password);
    public abstract void Logout();
    public abstract void DisplayInfo();   
}
