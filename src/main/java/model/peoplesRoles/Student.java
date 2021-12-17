package model.peoplesRoles;

public class Student {
    public int studentId;
    public int userId;
    public int classId;
    public int guardianId;
    public String phoneNumber;

    public Student(int studentId, int userId, int classId, int guardianId, String phoneNumber) {
        this.studentId = studentId;
        this.userId = userId;
        this.classId = classId;
        this.guardianId = guardianId;
        this.phoneNumber = phoneNumber;
    }
}
