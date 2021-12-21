package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLController {
    public static final String PASSWORD = "zxcv";
    public static final String USERNAME = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/dziennik";

    public static Connection Connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
