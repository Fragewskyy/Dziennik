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

        for (Student student : studentDAO.getAll()) {

            String query = "SELECT class_name from classes WHERE class_id = (select class_id from student where " +
                    "student_id = " + student.studentId + ");";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            try {
                System.out.println(student.studentId + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Current class: " + resultSet.getString("class_name"));
            } catch (SQLException e) {
                System.out.println(student.studentId + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Not assigned to any class");
            }
        }
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
