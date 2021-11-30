package model.peoplesRoles;

public class Teacher {
    int teacherId;
    int userId;

    public Teacher(int teacherId, int userId) {
        this.teacherId = teacherId;
        this.userId = userId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
