package controller.PrincipalActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Teacher;
import repository.TeacherDAO;

import java.sql.*;
import java.util.Scanner;

public class CreateNewClassAndAssignToTeacherAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAO();
        System.out.print("Type name of class: ");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.next();
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        int iter = 0;
        for (Teacher teacher : teacherDAO.getAll()) {
            iter+=1;
            String query = "SELECT name, surname FROM users WHERE user_id = " + teacher.userId + ";";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            System.out.println(teacher.teacherId + ": " + resultSet.getString("name") + " " + resultSet.getString("surname"));
        }
        System.out.print("\n" +
                "Select the number of the teacher you want to assign to the class: ");
        int choice = scanner.nextInt();

        String addingQuery =
                "INSERT INTO classes(class_name, teacher_id, students_amount) values('" + className + "'," + teacherDAO.get(choice).teacherId +
                ", 17);";
        statement.executeUpdate(addingQuery);
        System.out.println("Class succesfully added.");



    }

    @Override
    public String  getlabel() {
        return "create new class and assign to teacher";
    }
}
