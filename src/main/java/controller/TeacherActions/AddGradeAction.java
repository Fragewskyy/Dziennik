package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Student;
import repository.StudentDAO;
import repository.TeacherDAO;
import s.MainView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddGradeAction  implements Action {

    @Override
    public void executeQuery() {
        StudentDAO studentDAO = new StudentDAO();
        TeacherDAO teacherdao = new TeacherDAO();
        System.out.println("Select student you want to add a grade");
        Scanner scanner = new Scanner(System.in);
        String teacherid=teacherdao.getteacherid(MainView.getLogin());
        try {
            int iter=0;
            for (Student student : studentDAO.getAll()) {
                iter += 1;
                String query = "SELECT class_name from classes WHERE class_id = (select class_id from student where " +
                        "student_id = " + student.studentId + ");";
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                try {
                    System.out.println(iter + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Current class: " + resultSet.getString("class_name"));
                } catch (SQLException e) {
                    System.out.println(iter + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Not assigned to any class");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
        String show="SELECT grade_id,(SELECT subject_name FROM dziennik.subjects where dziennik.lessons.subject_id=dziennik.subjects.subject_id) ,grade ,date FROM dziennik.grades inner join dziennik.lessons on dziennik.grades.lesson=dziennik.lessons.lesson_id where \n" +
                "                student_id="+studentid+" and grades.lesson in (SELECT lesson_id FROM dziennik.lessons where teacher_id="+teacherid+")  ;";
    }

    @Override
    public String getlabel() {
        return null;
    }
}
