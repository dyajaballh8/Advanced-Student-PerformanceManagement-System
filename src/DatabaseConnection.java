
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * كلاس الاتصال بقاعدة البيانات (DatabaseConnection)
 * 
 * الكلاس ده هو المسؤول عن فتح الاتصال مع قاعدة بيانات SQL Server.
 * بيستخدم مشغل JDBC عشان يشبك مع قاعدة البيانات اللي اسمها 'StudentPerformanceDB'.
 */
public class DatabaseConnection {
    
    // رابط الاتصال بالسيرفر (Connection URL)
    // localhost:1433 ده العنوان والبورت العادي بتاع السيرفر.
    // databaseName=StudentPerformanceDB ده اسم قاعدة البيانات اللي بنشتغل عليها.
    // encrypt=true و trustServerCertificate=true دول مهمين عشان الاتصال يشتغل على الجهاز المحلي من غير مشاكل حماية.
    private static final String URL = "jdbc:sqlserver://localhost;instanceName=DYAA;databaseName=StudentPerformanceDB;encrypt=true;trustServerCertificate=true;";
    
    // بيانات الدخول - لازم تغير دول عشان يطابقوا إعدادات الـ SQL Server عندك
    // لو بتدخل بـ Windows Authentication في السيرفر بتاعك، أحسن حل إنك تفعل الـ SQL Auth وتعمل باسورد للـ 'sa'
    // أو تغير الـ URL وتضيف عليه: integratedSecurity=true; وتستخدم ملف mssql-jdbc_auth.dll
    private static final String USER = "sa"; // ده المستخدم الافتراضي (Admin) بتاع الـ SQL Server
    private static final String PASSWORD = "123456"; // كلمة السر اللي أنت عملتها وأنت بتسطب البرنامج

    /**
     * ميثود getConnection
     * 
     * @return كائن الاتصال (Connection Object) اللي هنستخدمه عشان نكلم الداتابيز
     * @throws SQLException لو حصلت مشكلة في الاتصال (مثلاً كلمة السر غلط أو السيرفر واقع)
     */
    public static Connection getConnection() throws SQLException {
        try {
            // بنحمل مشغل الـ JDBC بتاع SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            ensureSchema(conn); // بنتأكد إن الداتابيز فيها العمدان الجديدة المطلوبة
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found. Add mssql-jdbc jar to libraries.");
            throw new SQLException("JDBC Driver not found", e);
        }
    }

    /**
     * ميثود للتأكد من وجود الأعمدة الجديدة
     * بتجرب تضيف عمود Score و CreditHours لجدول CourseAbsence
     * لو العمود موجود أصلاً، هيطلع Error واحنا بنعمله ignonre (تجاهل)
     */
    private static void ensureSchema(Connection conn) {
        try (java.sql.Statement stmt = conn.createStatement()) {
            // محاولة إضافة عمود Score
            try {
                stmt.execute("ALTER TABLE CourseAbsence ADD Score FLOAT DEFAULT 0.0");
            } catch (SQLException ignored) {} // العمود موجود، مش مشكلة

            // محاولة إضافة عمود CreditHours
            try {
                stmt.execute("ALTER TABLE CourseAbsence ADD CreditHours INT DEFAULT 3");
            } catch (SQLException ignored) {} // العمود موجود، مش مشكلة
            
        } catch (SQLException e) {
            // أي خطأ تاني مش مهم يوقف البرنامج
            e.printStackTrace();
        }
    }
}
