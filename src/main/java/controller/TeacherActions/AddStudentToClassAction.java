package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Student;
import repository.ClassDAO;
import repository.StudentDAO;
import repository.TeacherDAO;
import repository.UserDAO;

import java.sql.*;
import java.util.Scanner;

public class AddStudentToClassAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        StudentDAO studentDAO = new StudentDAO();
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();

        System.out.print("Select the student you want to assign (Type ID): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        String query1 = "SELECT * FROM classes;";
        ResultSet resultSet1 = statement.executeQuery(query1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getInt("class_id") + ". " + resultSet1.getString("class_name"));
        }
        System.out.print("Select the class to which you want to assign the student: ");
        int choice1 = scanner.nextInt();
        String query2 = "UPDATE student SET class_id = " + choice1 + " WHERE student_id = " + choice + ";";
        statement.executeUpdate(query2);
        System.out.println("Student successfully assigned.");
    }

    @Override
    public String getlabel() {

        return "Add student to class.";
    }
}
