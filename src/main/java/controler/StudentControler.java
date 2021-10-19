package controler;

import java.sql.*;

public class StudentControler {

    Connection conn= DriverManager.getConnection()
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(QUERY)
}
