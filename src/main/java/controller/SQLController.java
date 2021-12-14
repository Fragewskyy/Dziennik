package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLController {
    public static final String PASSWORD = "zxcv";
    public static final String USERNAME = "root";
    public static final String URL = "localhost";

    public static void Connect() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
