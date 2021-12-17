package repository;

import controller.SQLController;
import model.peoplesRoles.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminDAO implements Dao {
    @Override
    public Admin get(int id) throws SQLException {
        String query = "SELECT * FROM dziennik.admin WHERE admin_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return new Admin(resultSet.getInt("admin_id"), resultSet.getInt("user_id"));
    }

    @Override
    public ArrayList<Admin> getAll() throws SQLException {
        ArrayList<Admin> result = new ArrayList<>();
        String query = "SELECT * FROM dziennik.admin;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            result.add(new Admin(resultSet.getInt("admin_id"), resultSet.getInt("user_id")));
        }

        return result;
    }

    @Override
    public void update(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nowe user_id: ");
        int u = scanner.nextInt();
        String query = "UPDATE dziennik.admin SET user_id = " + u + " WHERE admin_id = " + id + ";";
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

    public void save(Admin admin) throws SQLException {
        String query = "INSERT INTO dziennik.admin (user_id) VALUES (" + admin.admin_id + ");";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }
}
