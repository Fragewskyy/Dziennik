package controller.TeacherActions;

import controller.Action;
import controller.RepositoryHelper;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Student;
import repository.ClassDAO;
import repository.StudentDAO;
import repository.TeacherDAO;
import repository.UserDAO;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddStudentToClassAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        Scanner scanner = new Scanner(System.in);


        UserDAO userDAO = new UserDAO();
        StudentDAO studentDAO = new StudentDAO();
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        RepositoryHelper repositoryHelper=new RepositoryHelper();
        repositoryHelper.showStudentList();

        int choice;

        while (true) {
            System.out.print("Select the student you want to assign (Type ID): ");
            try {
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Try again!");
                scanner.next();
            }
        }
        String query1 = "SELECT * FROM classes;";
        ResultSet resultSet1 = statement.executeQuery(query1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getInt("class_id") + ". " + resultSet1.getString("class_name"));
        }

        int choice1;
        while (true) {
            System.out.print("Select the class to which you want to assign the student: ");
            try {
                choice1 = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Try again!");
                scanner.next();
            }
        }
        String query2 = "UPDATE student SET class_id = " + choice1 + " WHERE student_id = " + choice + ";";
        statement.executeUpdate(query2);
        System.out.println("Student successfully assigned.");
    }

    @Override
    public String getlabel() {

        return "Add student to class.";
    }
}
