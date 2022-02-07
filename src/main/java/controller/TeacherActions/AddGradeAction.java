package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Student;
import repository.StudentDAO;
import repository.TeacherDAO;
import repository.UserDAO;
import s.MainView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddGradeAction  implements Action {

    @Override
    public void executeQuery() {
        UserDAO userDAO = new UserDAO();
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
                Connection connection=SQLController.Connect();
                Statement statement = connection.createStatement();
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
        int choice=scanner.nextInt();
        int studentid= 0;
        try {
            studentid = studentDAO.getAll().get(choice-1).studentId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("what grade");
        String grade=scanner.next();
        String addgradeQuery="insert into dziennik.grades(grade,student_id,lesson_id) values("+grade+","+studentid+","+teacherdao.getlessonid()+");";
        Connection connection= null;
        try {
            connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(addgradeQuery);
            System.out.println("you added a grade");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public String getlabel() {
        return "Add student a grade.";
    }
}
