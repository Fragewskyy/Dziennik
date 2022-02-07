package controller.PrincipalActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Teacher;
import repository.TeacherDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateNewClassAndAssignToTeacherAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAO();
        String className;
        ArrayList<String> classesList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        String queryClasses = "SELECT class_name FROM dziennik.classes;";
        ResultSet classes = statement.executeQuery(queryClasses);
        while (classes.next()){
            classesList.add(classes.getString("class_name"));
        }
        while (true) {
            System.out.print("Type name of class: ");
            className = scanner.next();
            if (!(classesList.contains(className))){
                break;
            } else {
                System.out.println("Class name like that already existed! Type again.");
            }
        }
        int iter = 0;
        for (Teacher teacher : teacherDAO.getAll()) {
            iter+=1;
            String query = "SELECT name, surname FROM users WHERE user_id = " + teacher.userId + ";";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            System.out.println(teacher.teacherId + ": " + resultSet.getString("name") + " " + resultSet.getString("surname"));
        }
        int choice;
        while (true) {

            try {
                System.out.print("Select the number of the teacher you want to assign to the class(must be between 1 and " + iter +
                        "): ");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= iter) {
                    break;
                }
                System.out.println("Number must be an integer between 1 and " + iter + ".");

            } catch (InputMismatchException e) {
                System.out.println("Number must be an integer between 1 and " + iter + ".");
                scanner.next();
            }
        }

        String addingQuery =
                "INSERT INTO classes(class_name, teacher_id, students_amount) values('" + className + "'," + teacherDAO.get(choice).teacherId +
                ", 17);";
        statement.executeUpdate(addingQuery);
        System.out.println("Class succesfully added.");



    }

    @Override
    public String  getlabel() {
        return "Create new school class and assign teacher.";
    }
}
