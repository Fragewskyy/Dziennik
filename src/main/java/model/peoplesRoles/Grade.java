package model.peoplesRoles;

public class Grade {
    int grade;
    int studentId;
    int subject_id;

    public Grade(int grade, int studentId, int subject_id) {
        this.grade = grade;
        this.studentId = studentId;
        this.subject_id = subject_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
