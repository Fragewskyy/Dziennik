import menues.ViewforStudent;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("witamy na dzienniku lelktronicznym!!!");
        System.out.println("login:");
        System.out.println("password:");
        Scanner scanner=new Scanner(System.in);
        ViewforStudent viewforStudent=new ViewforStudent();
        viewforStudent.view(scanner.next(),scanner.next());
    }
}

