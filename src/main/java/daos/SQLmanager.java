package daos;

import daos.SQLconector;

import java.sql.*;

public class SQLmanager {
    public static String findidbylogin(String someoneslogin) throws SQLException {
        String query="SELECT users.password,users.login FROM dziennik.users WHERE users.login=`"+someoneslogin+"`;";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);

        return resultSet.getString("user_id");
    }
    public static int showRoleByLoginPassword(String login, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(),
                SQLconector.getPassword());
        String query="SELECT role_id FROM users WHERE login='"+login+"'AND password='"+password+"';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt("role_id");
    }
    public static String  find_ClassId_Byname(String classname) throws SQLException {
        String query="SELECT dziennik.clases_id FROM dziennik.clases WHERE clases.class_name=`"+classname+"`;";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);

        return resultSet.getString("class_id");
    }




}
