import daos.*;
import menues.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void Show_sample_text(){
        System.out.println("witamy na dzienniku lelktronicznym!!!");
        System.out.println("login:");
        System.out.println("password:");
    }



    public static void main(String[] args) throws SQLException {
        ViewforStudent viewforStudent=new ViewforStudent();
        ViewForAdmin viewForAdmin=new ViewForAdmin();
        ViewforGuardian viewforGuardian=new ViewforGuardian();
        ViewForPrincipal viewForPrincipal=new ViewForPrincipal();
        ViewforTeacher viewforTeacher=new ViewforTeacher();
        Scanner scanner=new Scanner(System.in);
        String login=scanner.next();
        String password=scanner.next();
        Show_sample_text();
        switch (Integer.parseInt(SQLmanager.showRoleby_Login_password(login,password))) {
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


    }


}

