package model.peoplesRoles;

public class Student {

    public int studentId;
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

    public Student(int studentId, int userId, Integer classId, Integer guardianId, String phoneNumber) {
        this.studentId = studentId;
        this.userId = userId;
        this.classId = classId;
        this.guardianId = guardianId;
        this.phoneNumber = phoneNumber;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(Integer guardianId) {
        this.guardianId = guardianId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
