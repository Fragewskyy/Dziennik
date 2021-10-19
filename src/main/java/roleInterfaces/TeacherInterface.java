package roleInterfaces;

import model.User;

public interface TeacherInterface {
    public void createstudent();
    public void createguardian();
    public void addStudenttoClass();
    public void modifygradeforst(int userid);
    public void generadeCertificate();
    public void createmultiplecertificate();
    public void addstudenttoguardian();
    public void addstudentAbsence();
    public void verifyabsece();


}
