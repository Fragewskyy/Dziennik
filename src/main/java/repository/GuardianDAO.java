package repository;

import controller.SQLController;
import model.peoplesRoles.Guardian;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GuardianDAO implements Dao{
    @Override
    public Guardian get(int id) throws SQLException {
        String query = "SELECT * FROM dziennik.guardian WHERE guardian_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return new Guardian(resultSet.getInt("guardian_id"), resultSet.getString("phone_number"), resultSet.getInt(
                "user_id"));
    }

    @Override
    public ArrayList<Guardian> getAll() throws SQLException {
        ArrayList<Guardian> result = new ArrayList<>();
        String query = "SELECT * FROM dziennik.guardian;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            result.add(new Guardian(resultSet.getInt("guardian_id"), resultSet.getString("phonenumber"), resultSet.getInt("user_id")));
        }

        return result;
    }


    public void save(Guardian guardian) throws SQLException {
        String query = "INSERT INTO dziennik.guardian(phonenumber, user_id) VALUES('" + guardian.phoneNumber + "', " + guardian.userId + ");";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);

    }

    @Override
    public void update(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type new user_id: ");
        int u = scanner.nextInt();
        System.out.print("Type new phone number to guardian: ");
        String p = scanner.next();
        String query =
                "UPDATE dziennik.guardian SET user_id = " + u + ", phonenumber = '" + p + "' WHERE admin_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dziennik.guardian WHERE guardian_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);

    }
}
