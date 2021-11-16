import menues.ViewforStudent;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("witamy na dzienniku lelktronicznym!!!");
        System.out.println("login:");
        System.out.println("password:");
        ViewforStudent viewforStudent=new ViewforStudent();
        viewforStudent.view();
    }
}

