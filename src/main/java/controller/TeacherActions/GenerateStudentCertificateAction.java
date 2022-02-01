package controller.TeacherActions;

import controller.Action;
import controller.GenerateCertificate;
import controller.SQLController;
import model.peoplesRoles.Student;
import repository.StudentDAO;
import repository.UserDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GenerateStudentCertificateAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        StudentDAO studentDAO = new StudentDAO();

        Connection connection= SQLController.Connect();
        Statement statement= connection.createStatement();

        int iter = 0;

        for (Student student : studentDAO.getAll()) {
            iter+=1;
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
                if(choice>=1 && choice<=iter) {
                    break;
                }
                System.out.println("Try again!");
            } catch (InputMismatchException e) {
                System.out.println("Try again!");
                scanner.next();
            }
        }
        Student student = studentDAO.get(choice);
        int index=1;


            GenerateCertificate generateCertificate = new GenerateCertificate();
            String getuserNameAndSurnameQuery="SELECT name,surname FROM dziennik.users where user_id="+student.userId+";";
            try {
                ResultSet resultSet = statement.executeQuery(getuserNameAndSurnameQuery);
                while (resultSet.next()) {
                    System.out.println(index+":"+resultSet.getString("name")+" "+resultSet.getString("surname"));
                    index++;
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                generateCertificate.executeQuery(choice);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    @Override
    public String getlabel() {

        return "Generate student's certificate.";
    }
}
