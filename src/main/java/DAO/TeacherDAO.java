package DAO;

import Service.SQLconector;
import roleInterfaces.TeacherInterface;

import java.sql.*;

public class TeacherDAO {
    public static int getTeacherIdByNameAndSurname(String name, String surname) throws SQLException {
        String query = "SELECT teacher_id FROM teacher WHERE user_id = (SELECT user_id FROM users WHERE name = '" + name + "' AND surname = '" + surname + "');";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(),SQLconector.getPassword());
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        return resultSet.getInt("teacher_id");
    }

}
