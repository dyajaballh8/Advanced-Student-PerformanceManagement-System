 
public class FacultyFrame extends javax.swing.JFrame {

    /**
     * Creates new form FacultyFrame
     */
    public FacultyFrame() {
        initComponents();
    }

     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Faculty = new javax.swing.JLabel();
        subject = new javax.swing.JLabel();
        studentid = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        savebu = new javax.swing.JButton();
        subtext = new javax.swing.JTextField();
        stutext = new javax.swing.JTextField();
        scotext = new javax.swing.JTextField();
        backgrondfa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Faculty.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        Faculty.setText("Faculty");
        getContentPane().add(Faculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 22, -1, -1));

        subject.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subject.setText("Subject");
        getContentPane().add(subject, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 104, -1, -1));

        studentid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        studentid.setText("Student ID");
        getContentPane().add(studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 189, -1, -1));

        score.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        score.setText("Score");
        getContentPane().add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 284, -1, -1));

        savebu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        savebu.setText("Save");
        savebu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuActionPerformed(evt);
            }
        });
        getContentPane().add(savebu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 356, 96, 29));

        subtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtextActionPerformed(evt);
            }
        });
        getContentPane().add(subtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 108, 172, -1));
        getContentPane().add(stutext, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 193, 172, -1));
        getContentPane().add(scotext, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 288, 172, -1));

        javax.swing.JLabel absence = new javax.swing.JLabel();
        absence.setFont(new java.awt.Font("Segoe UI", 1, 18));
        absence.setText("Absence");
        getContentPane().add(absence, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 320, -1, -1));

        abstext = new javax.swing.JTextField();
        getContentPane().add(abstext, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 326, 172, -1));

        backgrondfa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facture.jpg"))); // NOI18N
        getContentPane().add(backgrondfa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtextActionPerformed

    /**
     * ميثود savebuActionPerformed
     * بتشتغل لما ندوس على زرار "Save".
     * بتحدث درجة الطالب في الداتابيز.
     */
    private void savebuActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // 1. البيانات
        String subject = subtext.getText().trim();
        String studentIdStr = stutext.getText().trim();
        String scoreStr = scotext.getText().trim();
        String absenceStr = abstext.getText().trim(); // خانة الغياب الجديدة

        if (subject.isEmpty() || studentIdStr.isEmpty() || scoreStr.isEmpty() || absenceStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int studentId = Integer.parseInt(studentIdStr);
            double score = Double.parseDouble(scoreStr);
            int absenceDays = Integer.parseInt(absenceStr); // عدد أيام الغياب في المادة دي

            try (java.sql.Connection conn = DatabaseConnection.getConnection()) {
                
                // --- الخطوة 1: تحديث درجة المادة + عدد أيام الغياب في جدول CourseAbsence ---
                String updateCourseSql = "UPDATE CourseAbsence SET Score = ?, AbsentLectures = ? WHERE StudentID = ? AND CourseName = ?";
                try (java.sql.PreparedStatement stmt = conn.prepareStatement(updateCourseSql)) {
                    stmt.setDouble(1, score);
                    stmt.setInt(2, absenceDays);
                    stmt.setInt(3, studentId);
                    stmt.setString(4, subject);
                    int rows = stmt.executeUpdate();
                    
                    if (rows == 0) {
                         javax.swing.JOptionPane.showMessageDialog(this, "Course not found for this Student.\nMake sure the Subject name matches exactly.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                         return;
                    }
                }
                
                // --- الخطوة 2: إعادة حساب المعدل التراكمي (Global GPA) ---
                String calcSql = "SELECT Score, CreditHours FROM CourseAbsence WHERE StudentID = ?";
                double totalPoints = 0;
                int totalHours = 0;
                
                try (java.sql.PreparedStatement stmt = conn.prepareStatement(calcSql)) {
                    stmt.setInt(1, studentId);
                    java.sql.ResultSet rs = stmt.executeQuery();
                    while(rs.next()) {
                        double s = rs.getDouble("Score");
                        int h = rs.getInt("CreditHours");
                        totalPoints += s * h;
                        totalHours += h;
                    }
                }
                
                double finalGPA = 0.0;
                if (totalHours > 0) {
                   finalGPA = (totalPoints / totalHours / 100.0) * 4.0;
                }

                String updateStudentSql = "UPDATE Students SET Score = ? WHERE StudentID = ?";
                try (java.sql.PreparedStatement stmt = conn.prepareStatement(updateStudentSql)) {
                    stmt.setDouble(1, finalGPA);
                    stmt.setInt(2, studentId);
                    stmt.executeUpdate();
                }
                
                // --- الخطوة 3: تحديث الحضور الكلي (Attendance Table) ---
                // عشان الـ AlertManager يشتغل صح، لازم نحدث جدول Attendance بمجموع أيام الحضور.
                // هنفترض إن الكلية فيها عدد أيام ثابت (مثلاً 80 أو الموجود في الداتابيز) وهنطرح منه مجموع الغياب في كل المواد.
                
                // أ) نحسب مجموع الغياب في كل المواد
                String totalAbsenceSql = "SELECT SUM(AbsentLectures) as TotalAbsence FROM CourseAbsence WHERE StudentID = ?";
                int totalStudentAbsence = 0;
                try (java.sql.PreparedStatement stmt = conn.prepareStatement(totalAbsenceSql)) {
                    stmt.setInt(1, studentId);
                    java.sql.ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        totalStudentAbsence = rs.getInt("TotalAbsence");
                    }
                }
                
                // ب) نجيب إجمالي الأيام الدراسية ونحدث أيام الحضور
                String updateAttendanceSql = "UPDATE Attendance SET AttendedDays = TotalDays - ? WHERE StudentID = ?";
                try (java.sql.PreparedStatement stmt = conn.prepareStatement(updateAttendanceSql)) {
                     stmt.setInt(1, totalStudentAbsence);
                     stmt.setInt(2, studentId);
                     stmt.executeUpdate();
                }

                javax.swing.JOptionPane.showMessageDialog(this, "Data Saved Successfully!\nNEW GPA: " + String.format("%.2f", finalGPA) + "\nTotal Absence: " + totalStudentAbsence);

            } catch (Exception e) {
                e.printStackTrace();
                 javax.swing.JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "ID, Score and Absence must be numbers", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FacultyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacultyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacultyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacultyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacultyFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Faculty;
    private javax.swing.JLabel backgrondfa;
    private javax.swing.JButton savebu;
    private javax.swing.JLabel score;
    private javax.swing.JTextField scotext;
    private javax.swing.JLabel studentid;
    private javax.swing.JTextField stutext;
    private javax.swing.JLabel subject;
    private javax.swing.JTextField subtext;
    private javax.swing.JTextField abstext;
    // End of variables declaration//GEN-END:variables
}
