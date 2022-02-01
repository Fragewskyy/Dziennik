package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;
import s.MainView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertStudentToGuardian implements Action {
    @Override
    public void executeQuery() {
        UserDAO userDAO = new UserDAO();
        StudentDAO studentDAO=new StudentDAO();
        GuardianDAO guardianDAO=new GuardianDAO();
        System.out.println("choose your student");
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
        Scanner scanner = new Scanner(System.in);
        int choice=scanner.nextInt();

        String studentid=studentDAO.getstudentid(scanner.next());
        String guardianid=guardianDAO.getguardianid(MainView.getLogin());

        try {
            String query="Update dziennik.student SET guardian_id="+guardianid+" where student_id="+studentDAO.getAll().get(choice-1)+";";
            Connection connection= SQLController.Connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("new guardian has been inserted :)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getlabel() {
        return "Assign student to a guardian.";
    }
}
