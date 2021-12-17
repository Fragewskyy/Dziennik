package repository;

import controller.SQLController;
import model.User;
import model.peoplesRoles.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO implements Dao{

    public String getRoleByLogin(String login) throws SQLException {
        String query =
                "SELECT role_name FROM role WHERE role_id = (SELECT role_id from users WHERE login = '" + login + "');";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return resultSet.getString("role_name");
    }

    public int getId(String login) throws SQLException {
        String query = "SELECT user_id FROM users WHERE login = '" + login + "';";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return resultSet.getInt("user_id");
    }


    @Override
    public User get(int id) throws SQLException {
        String query = "SELECT * FROM dziennik.users WHERE user_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"),
                resultSet.getString("surname"), resultSet.getInt("role_id"));
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        String query = "SELECT * FROM dziennik.users;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            result.add(new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"),
                    resultSet.getString("surname"), resultSet.getInt("role_id")));
        }

        return result;
    }

    @Override
    public void update(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type new login: ");
        String l = scanner.next();
        System.out.print("Type new password: ");
        String p = scanner.next();
        System.out.print("Type new name: ");
        String n = scanner.next();
        System.out.print("Type new surname: ");
        String s = scanner.next();
        System.out.print("Type new role_id: ");
        int r = scanner.nextInt();
        String query = "UPDATE dziennik.users SET login = '" + l + "', password = '" + p + "', name = '" + n + "'," +
                " " +
                "surname = '" + s + "', role_id = " + r + " WHERE user_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);

    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dziennik.users WHERE user_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    public void save(User user) throws SQLException {
        String query =
                "INSERT INTO dziennik.users (login, password, name, surname) VALUES ('" + user.login + "', '" + user.password + "', '" + user.name + "', '" + user.surname + "', " + user.roleId + ");";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }
}
