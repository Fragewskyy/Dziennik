package model.peoplesRoles;

public class Teacher {
    public int teacherId;
    public int userId;
    public int subject_id;

    public Teacher(int teacherId, int userId, int subject_id) {
        this.teacherId = teacherId;
        this.userId = userId;
        this.subject_id = subject_id;
    }

    public Teacher(int teacherId, int userId) {
        this.teacherId = teacherId;
        this.userId = userId;
    }
}
