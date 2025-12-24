 
public class StudentFrame extends javax.swing.JFrame {

    /**
     * Creates new form StudentFrame
     */
    private void makeReadOnly() {
     namtext.setEditable(false);
     sectext.setEditable(false);
     gpatext.setEditable(false);
 
     jTable2.setEnabled(false);
     }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        student = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        namtext = new javax.swing.JTextField();
        section = new javax.swing.JLabel();
        sectext = new javax.swing.JTextField();
        gpa = new javax.swing.JLabel();
        gpatext = new javax.swing.JTextField();
        logoutbu = new javax.swing.JButton();
        table = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        backgrandst = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        student.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        student.setText("Student");
        getContentPane().add(student, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        name.setText("Name");
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 40));
        getContentPane().add(namtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 210, -1));

        section.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        section.setText("Section");
        getContentPane().add(section, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        getContentPane().add(sectext, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 60, -1));

        gpa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        gpa.setText("GPA");
        getContentPane().add(gpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));
        getContentPane().add(gpatext, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 70, -1));

        logoutbu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        logoutbu.setText("Logout");
        getContentPane().add(logoutbu, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Subject", "Score", "Number of hours", "Absences"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.setViewportView(jTable2);

        getContentPane().add(table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 410, 220));

        backgrandst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facture.jpg"))); // NOI18N
        backgrandst.setText(" ");
        getContentPane().add(backgrandst, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int currentStudentId;

    /**
     * الكونستركتور (Constructor)
     * @param studentId ده بيتبعت من الـ LoginFrame عشان نعرف مين الطالب اللي عامل دخول.
     */
    public StudentFrame(int studentId) {
        this.currentStudentId = studentId;
        initComponents();
        makeReadOnly(); // بنتأكد إن الخانات للقراءة فقط ومينفعش الطالب يعدل فيها
        loadStudentData(); // بنحمل البيانات ونعرضها من الداتابيز
        
        // زرار الخروج (Logout Button)
        logoutbu.addActionListener(e -> {
            new LoginFrame().setVisible(true); // نرجع لشاشة الدخول
            this.dispose(); // نقفل الشاشة دي
        });
    }

    /**
     * ميثود loadStudentData
     * بتتصل بالداتابيز وبتجيب:
     * 1. بيانات الطالب الشخصية (الاسم، التخصص، المعدل التراكمي)
     * 2. سجل الحضور والغياب للمواد
     */
    private void loadStudentData() {
        try (java.sql.Connection conn = DatabaseConnection.getConnection()) {
            
            // --- الجزء الأول: تحميل بيانات الطالب الأساسية ---
            String sql = "SELECT Name, Major, Score, FacultyName FROM Students WHERE StudentID = ?";
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, currentStudentId);
                java.sql.ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // بنملأ الـ GUI بالبيانات اللي جت من الداتابيز
                    namtext.setText(rs.getString("Name"));
                    sectext.setText(rs.getString("Major")); // بنستخدم الـ Major مكان القسم
                    gpatext.setText(String.valueOf(rs.getDouble("Score"))); // بنعرض المعدل التراكمي (GPA)
                }
            }
            
            // --- الجزء الثاني: تحميل بيانات المواد والغياب ---
            // بنجيب تفاصيل المواد والغياب للطالب ده.
            // بنستخدم جدول 'CourseAbsence' لأنه اللي بيربط الطالب بالمواد والمحاضرات.
            // --- الجزء الثاني: تحميل بيانات المواد والغياب ---
            // بنجيب تفاصيل المواد والغياب للطالب ده.
            // بنربط مع جدول Courses عشان نجيب كود المادة (CourseCode)
            String courseSql = 
                "SELECT ca.CourseName, ca.TotalLectures, ca.AbsentLectures, ca.Score, c.CourseCode " +
                "FROM CourseAbsence ca " +
                "LEFT JOIN Courses c ON ca.CourseName = c.CourseName " +
                "WHERE ca.StudentID = ?";
            
            // بنجيب الموديل بتاع الجدول عشان نضيف فيه صفوف
            // ضفنا عمود "Code" في الأول
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "Code", "Subject", "Score", "Hours", "Absences", "Status"
                }
            );
            jTable2.setModel(model);
            
            AlertManager alertChecker = new AlertManager(currentStudentId, 0);

            try (java.sql.PreparedStatement stmt = conn.prepareStatement(courseSql)) {
                stmt.setInt(1, currentStudentId);
                java.sql.ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    String subject = rs.getString("CourseName");
                    int totalHours = rs.getInt("TotalLectures"); // This is actually credit hours in our logic usually, but stored as TotalLectures here
                    int absences = rs.getInt("AbsentLectures");
                    double score = rs.getDouble("Score");
                    String code = rs.getString("CourseCode");
                    
                    // لو الكود مش موجود (null) بنحط شرطة
                    if (code == null) code = "N/A";
                    
                    String status = alertChecker.getCourseStatus(absences);
                    
                    // بنضيف صف جديد للجدول
                    model.addRow(new Object[]{code, subject, score, totalHours, absences, status});
                }
            }

            // --- الجزء الثالث: استخدام AlertManager لفحص الحضور الكلي ---
            // بنجيب بيانات الحضور الكلي من جدول Attendance
            String attendanceSql = "SELECT TotalDays, AttendedDays FROM Attendance WHERE StudentID = ?";
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(attendanceSql)) {
                stmt.setInt(1, currentStudentId);
                java.sql.ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    int totalDays = rs.getInt("TotalDays");
                    int attendedDays = rs.getInt("AttendedDays");

                    // بنستخدم الكلاس الجديد AlertManager
                    AlertManager alertManager = new AlertManager(currentStudentId, totalDays);
                    
                    // بنشيك لو فيه تنبيه (الغياب كتير)
                    if (alertManager.AttendanceAlert(attendedDays)) {
                        // بنجهز رسالة التقرير
                        String report = alertManager.getAttendanceReport(attendedDays);
                        // بنعرض رسالة تحذيرية
                        javax.swing.JOptionPane.showMessageDialog(this, report, "Attendance Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // لو حصل خطأ واحنا بنحمل البيانات بنطلع رسالة
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }


     
    public static void main(String args[]) {
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentFrame(1).setVisible(true); // Test with ID 1
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgrandst;
    private javax.swing.JLabel gpa;
    private javax.swing.JTextField gpatext;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton logoutbu;
    private javax.swing.JLabel name;
    private javax.swing.JTextField namtext;
    private javax.swing.JTextField sectext;
    private javax.swing.JLabel section;
    private javax.swing.JLabel student;
    private javax.swing.JScrollPane table;
    // End of variables declaration//GEN-END:variables
}
