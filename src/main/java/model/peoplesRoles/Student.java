package model.peoplesRoles;

public class Student {
    public int studentId;
    public int userId;
    public Integer classId;
    public Integer guardianId;
    public String phoneNumber;

    public Student(int studentId, int userId, Integer classId,Integer guardianId, String phoneNumber) {
        this.studentId = studentId;
        this.userId = userId;
        this.classId = classId;
        this.guardianId = guardianId;
        this.phoneNumber = phoneNumber;
    }

}
