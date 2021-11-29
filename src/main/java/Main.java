import daos.*;
import menues.*;

import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {


        ViewforStudent viewforStudent = new ViewforStudent();
        ViewForAdmin viewForAdmin = new ViewForAdmin();
        ViewforGuardian viewforGuardian = new ViewforGuardian();
        ViewForPrincipal viewForPrincipal = new ViewForPrincipal();
        ViewforTeacher viewforTeacher = new ViewforTeacher();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Witamy w dzienniku elektronicznym!");
        System.out.println("Podaj twój login: ");
        String login = scanner.next();
        System.out.println("Podaj twoje hasło: ");
        String password = scanner.next();

        switch (SQLmanager.showRoleByLoginPassword(login,password)) {
            case 1:
                viewforStudent.view(login,password);
                break;
            case 2:
                viewforGuardian.view();
                break;
            case 3:
                viewforTeacher.view();
                break;
            case 4:
                viewForPrincipal.view();
                break;
            case 5:
                viewForAdmin.view();
                break;
        }
//        Podaj twój login:
//        eandrys0@virginia.edu
//        Podaj twoje hasło:
//        6T90fyNtNaw


    }


}

