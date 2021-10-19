package roleInterfaces;

import java.util.List;

public interface GuardianInterface {
    public void displayGrades(int student_id);
    public void displayGradesOfAllMyStudents();
    public void displayAbsences(int student_id);
    public void displayTooLowGrades(int guardian_id);
    public void sendAnExcuse(int absence_id);

}
