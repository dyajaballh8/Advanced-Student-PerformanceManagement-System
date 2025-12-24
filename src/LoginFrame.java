
public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        Username = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        ID = new javax.swing.JLabel();
        passField = new javax.swing.JTextField();
        backgraund = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        Title.setText("Welcome");
        getContentPane().add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 54, 198, -1));

        loginBtn.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        getContentPane().add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 361, -1, -1));

        Username.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Username.setText("Username");
        getContentPane().add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 174, -1, -1));
        getContentPane().add(userField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 212, 287, -1));

        ID.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        ID.setText("Password");
        getContentPane().add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 252, -1, -1));
        getContentPane().add(passField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 296, 287, -1));

        backgraund.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facture.jpg"))); // NOI18N
        getContentPane().add(backgraund, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -6, 410, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
    String username = userField.getText().trim();
    String password = passField.getText().trim(); // بنتأكد إن الخانة دي واخدة الباسورد

    // التحقق: بنتأكد إن الخانات مش فاضية
    if (username.isEmpty() || password.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return; // بنقف هنا لو مفيش بيانات
    }

    // عملية الداتابيز
    // بنحاول نتصل بالداتابيز وندور على مستخدم بالبيانات دي
    try (java.sql.Connection conn = DatabaseConnection.getConnection();
         // بنستخدم PreparedStatement عشان نمنع الـ SQL Injection (حماية وأمان)
         java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT UserID, Role FROM Users WHERE Username = ? AND Password = ?")) {
        
        stmt.setString(1, username); // بنحط الاسم مكان أول علامة استفهام
        stmt.setString(2, password); // بنحط الباسورد مكان تاني علامة استفهام
        java.sql.ResultSet rs = stmt.executeQuery(); // بننفذ الاستعلام

        if (rs.next()) {
             // لو rs.next() جابت نتيجة، يبقى المستخدم موجود (تسجيل دخول ناجح)
             int userId = rs.getInt("UserID");
             String role = rs.getString("Role");

             // توجيه حسب الدور (Role-based Navigation)
             // بنشوف الدور إيه ونفتح الشاشة المناسبة ليه
             if ("Student".equalsIgnoreCase(role)) {
                 StudentFrame sf = new StudentFrame(userId); // بنبعت الـ UserID عشان نحمل بيانات الطالب ده بس
                 sf.setVisible(true);
                 this.dispose(); // بنقفل شاشة الدخول
             } else if ("Faculty".equalsIgnoreCase(role)) {
                 FacultyFrame ff = new FacultyFrame();
                 ff.setVisible(true);
                 this.dispose(); // بنقفل شاشة الدخول
             } else {
                 // لو الدور مش معروف أو مش student/faculty
                 javax.swing.JOptionPane.showMessageDialog(this, "Unknown Role: " + role, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
             }

        } else {
            // مفيش مستخدم بالبيانات دي (فشل الدخول)
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    } catch (java.sql.SQLException ex) {
        // لو حصلت مشكلة في الاتصال بالداتابيز
        ex.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    
    
    }//GEN-LAST:event_loginBtnActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Username;
    private javax.swing.JLabel backgraund;
    private javax.swing.JButton loginBtn;
    private javax.swing.JTextField passField;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
