package model.peoplesRoles;

public class Student {

    public int userId;
    public Integer classId;
    public Integer guardianId;
    public String phoneNumber;

    public Student(Integer classId, Integer guardianId, String phoneNumber) {
        this.classId = classId;
        this.guardianId = guardianId;
        this.phoneNumber = phoneNumber;
    }

    public Student(int userId, Integer classId, Integer guardianId, String phoneNumber) {

        this.userId = userId;
        this.classId = classId;
        this.guardianId = guardianId;
        this.phoneNumber = phoneNumber;
    }
}
