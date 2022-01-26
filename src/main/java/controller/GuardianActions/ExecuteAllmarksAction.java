package controller.GuardianActions;

import controller.Action;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Guardian;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;
import view.MainView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExecuteAllmarksAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select student_id from student where guardian_id = " + guardian.guardianId + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME, SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int iter = 0;
        Integer[] studentIds = new Integer[1000];
        while (resultSet.next()) {
            iter += 1;
            Student student = studentDAO.get(resultSet.getInt("student_id"));
            User user = userDAO.get(student.userId);
            studentIds[iter-1] = resultSet.getInt("student_id");
            System.out.println(iter + ". " + user.name + " " + user.surname);
        }


        int choice;
        while (true) {
            System.out.print("Select the number of student whose grades you want to see(must be between 1 and " + iter +
                    "): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= iter) {
                    break;
                }

            } catch (InputMismatchException e) {
                scanner.next();
            }
        }

        String query1 = "SELECT grade, student_id, (select lesson_date from lessons where lessons.lesson_id = grades" +
                ".lesson_id) as lessonD, (select subject_name from subjects where subjects.subject_id = (select " +
                "subject_id from lessons where lessons.lesson_id = grades.lesson_id)) as subjectN from grades where " +
                "student_id = " + studentIds[choice-1] + ";";

        ResultSet resultSet1 = statement.executeQuery(query1);

        Student student = studentDAO.get(studentIds[choice-1]);
        User user = userDAO.get(student.userId);

        System.out.println(user.name + " " + user.surname + "'s grades: ");
        while (resultSet1.next()) {
            System.out.println("- Grade: " + resultSet1.getInt("grade") + " | Subject: " + resultSet1.getString(
                    "subjectN") + " | Date: " + resultSet1.getDate("lessonD"));
        }


    }

    @Override
    public String getlabel() {
        return "Display all grades of selected students.";
    }
}
