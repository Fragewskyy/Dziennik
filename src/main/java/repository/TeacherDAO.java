package repository;

import controller.SQLController;
import model.peoplesRoles.Admin;
import model.peoplesRoles.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TeacherDAO implements Dao{

    @Override
    public Teacher get(int id) throws SQLException {
        String query = "SELECT * FROM dziennik.teacher WHERE admin_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return new Teacher(resultSet.getInt("teacher_id"), resultSet.getInt("user_id"));
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException {
        ArrayList<Teacher> result = new ArrayList<>();
        String query = "SELECT * FROM dziennik.teacher;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            result.add(new Teacher(resultSet.getInt("teacher_id"), resultSet.getInt("user_id")));
        }

        return result;
    }

    @Override
    public void update(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nowe user_id: ");
        int u = scanner.nextInt();
        String query = "UPDATE dziennik.teacher SET user_id = " + u + " WHERE teacher_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dziennik.admin WHERE admin_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    public void save(Teacher teacher) throws SQLException {
        String query = "INSERT INTO dziennik.teacher (user_id) VALUES (" + teacher.userId + ");";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }
}
