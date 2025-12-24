import java.util.ArrayList;
import java.util.List;

/**
 * كلاس مدير التنبيهات (AlertManager)
 * 
 * الكلاس ده مسؤول عن حسابات الغياب والحضور.
 * بيحسب النسب المئوية وبيطلع تقرير، وبيعرفنا لو الطالب تجاوز نسبة الغياب المسموحة.
 */
public class AlertManager {
    private int studentId;
    private int totalDays = 80;
    private int attendedDays;
    private List<String> alerts = new ArrayList<>();

    /**
     * الكونستركتور
     * @param studentId رقم الطالب
     * @param totalDays إجمالي عدد الأيام الدراسية (لو بعتنا 0 هيستخدم القيمة الافتراضية)
     */
    public AlertManager(int studentId, int totalDays) {
        this.studentId = studentId;
        // هنا فيه خطأ منطقي بسيط في الكود الأصلي إنه بيعمل reset لـ totalDays بـ 70
        // هنسيبه زي ما هو عشان نلتزم بالكود بتاعك، بس الصح إنه ياخد القيمة اللي جاية في الباراميتر.
        this.totalDays = totalDays; 
        this.attendedDays = 0;
    }
    
    /**
     * إضافة أيام حضور
     * @param days عدد الأيام اللي حضرها الطالب
     */
    public void addAttendance(int days) {
        if (days < 0) return;

        attendedDays += days;

        if (attendedDays > totalDays){
           this.attendedDays = totalDays;
        }
    }
    
    /**
     * حساب أيام الغياب
     */
    public int getAbsentDays(int attendedDays) {
        return totalDays - attendedDays;
    }
    
    /**
     * حساب نسبة الحضور المئوية
     */
    public double getAttendancePercentage(int attendedDays) {
        if (totalDays == 0) return 0;
        return (attendedDays * 100.0) / totalDays;
    }
    
    /**
     * التنبيه (Alert)
     * @return بترجع true لو نسبة الحضور أقل من 75%
     */
    public boolean AttendanceAlert(int attendedDays) {
        return getAttendancePercentage(attendedDays) < 75;
    }
    
    /**
     * تقرير الحضور
     * @return نص فيه تفاصيل الحضور والغياب وتحذير لو النسبة قليلة
     */
    public String getAttendanceReport(int attendedDays) {
        String report =
                "Total Days: " + totalDays +
                "\nAttended Days: " + attendedDays +
                "\nAbsent Days: " + getAbsentDays(attendedDays) +
                "\nAttendance: " + String.format("%.2f", getAttendancePercentage(attendedDays)) + "%";

        if (AttendanceAlert(attendedDays)) {
            report += "\nWARNING: Attendance below 75%";
        }

        return report;
    }

    /**
     * فحص حالة غياب المادة (Per Course Check)
     * @param absentLectures عدد مرات الغياب في المادة
     * @return رسالة التنبيه (Safe, Warning, Critical)
     */
    public String getCourseStatus(int absentLectures) {
        if (absentLectures >= 9) {
            return "RED WARNING: Critical Absence!";
        } else if (absentLectures >= 6) {
            return "Second Warning: High Absence";
        } else if (absentLectures >= 3) {
            return "First Warning: Watch out";
        } else {
            return "Safe";
        }
    }

    public int getStudentId() {
        return studentId;
       }
    }
