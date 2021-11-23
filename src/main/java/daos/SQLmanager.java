package daos;

import daos.SQLconector;

import java.sql.*;

public class SQLmanager {
    public static String findidbylogin(String someoneslogin) throws SQLException {
        String query="SELECT password,login FROM users WHERE login='"+someoneslogin+"';";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);

        return resultSet.getString("user_id");
    }
    public static String showRoleby_Login_password(String login, String password) throws SQLException {
        String query="SELECT roles.role_id FROM roles WHERE login='"+login+"'AND password='"+password+"';";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);
        return resultSet.getString("role_id");
    }



}
