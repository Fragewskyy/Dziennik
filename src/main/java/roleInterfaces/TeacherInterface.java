package roleInterfaces;

import model.User;

import java.sql.SQLException;

public interface TeacherInterface {
    public void createStudent() throws SQLException;
    public void createGuardian();
    public void addStudentToClass();
    public void modifyGrade();
    public void generateCertificate();
    public void createMultipleCertificate();
    public void addStudentToGuardian();
    public void addStudentAbsence();
    public void verifyAbsenceExcuse();


}
