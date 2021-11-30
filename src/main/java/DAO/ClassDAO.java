package DAO;

import Service.SQLconector;

import java.sql.*;

public class ClassDAO {
    public static int getClassIdByName(String className) throws SQLException {
        String query = "SELECT class_id FROM classes WHERE class_name ='" + className + "';";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(),SQLconector.getPassword());
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return resultSet.getInt("class_id");

    }
}
