package repository;

import controller.SQLController;
import model.peoplesRoles.Admin;
import model.peoplesRoles.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static controller.SQLController.PASSWORD;

public class StudentDAO implements Dao{
    public String getstudentid( String login ){
        String query="SELECT student_id  FROM dziennik.students where user_id=(SELECT user_id FROM dziennik.users where login='"+login+"');";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return String.valueOf(resultSet.getInt("student_id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ///tu będzie error spokojnie
        return "error";
    }


    @Override
    public Student get(int id) throws SQLException {
        String query = "SELECT * FROM dziennik.student WHERE student_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return new Student( resultSet.getInt("user_id"), resultSet.getInt("class_id"),
                resultSet.getInt("guardian_id"), resultSet.getString("phone_number"));
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException {
        ArrayList<Student> result = new ArrayList<>();
        String query = "SELECT * FROM dziennik.student;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            result.add(new Student( resultSet.getInt("user_id"), resultSet.getInt("class_id"),
                    resultSet.getInt("guardian_id"), resultSet.getString("phone_number")));
        }

        return result;
    }

    @Override
    public void update(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type new user_id: ");
        int u = scanner.nextInt();
        System.out.print("Type new class_id: ");
        int c = scanner.nextInt();
        System.out.print("Type new guardian_id: ");
        int g = scanner.nextInt();
        System.out.print("Type new phone number: ");
        String p = scanner.next();
        String query =
                "UPDATE dziennik.student SET user_id = " + u + ", class_id = " + c + ", guardian_id = " + g + ", " +
                        "phone_number = '" + p + "' WHERE student_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);


    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dziennik.student WHERE student_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    public void save(Student student) throws SQLException {
        String query =
                "INSERT INTO dziennik.students (user_id, class_id, teacher_id, phone_number) VALUES (" + student.userId + ", " + student.classId + ", " + student.guardianId + ", '" + student.phoneNumber + "');";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

}
