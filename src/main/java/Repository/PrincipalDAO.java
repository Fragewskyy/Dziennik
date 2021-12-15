package Repository;

import Controller.SQLController;
import model.peoplesRoles.Admin;
import model.peoplesRoles.Principal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalDAO implements Dao{
    @Override
    public Principal get(int id) throws SQLException {
        String query = "SELECT * FROM dziennik.principal WHERE principal_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return new Principal(resultSet.getInt("principal_id"), resultSet.getInt("user_id"));
    }

    @Override
    public ArrayList<Principal> getAll() throws SQLException {
        ArrayList<Principal> result = new ArrayList<>();
        String query = "SELECT * FROM dziennik.principal;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            result.add(new Principal(resultSet.getInt("principal_id"), resultSet.getInt("user_id")));
        }

        return result;
    }

    public void save(Principal principal) throws SQLException {
        String query = "INSERT INTO dziennik.principal (user_id) VALUES (" + principal.userId + ");";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);

    }

    @Override
    public void update(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nowe user_id: ");
        int u = scanner.nextInt();
        String query = "UPDATE dziennik.principal SET user_id = " + u + " WHERE principal_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);

    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dziennik.principal WHERE principal_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);

    }
}
