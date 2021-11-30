package menues;

import Service.UserService;
import Service.studentSevice;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewforStudent {

    public void view(String login, String password) throws SQLException {
        studentSevice studentDAO=new studentSevice();
        UserService userDAO=new UserService();
        System.out.println("Click :");
        System.out.println("- 'x' -> logout");
        System.out.println("- 1 -> Send message");
        System.out.println("- 2 -> Display your grades");
        System.out.println("- 3 -> Display your absences ");
        System.out.println("- 4 -> Change password");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.next();
        while (input!="x") {

            if (input.equals("1")) {
                userDAO.sendTextMessage();
            }
            if (input.equals("2")) {
                studentDAO.displayAllGrades(login, password);
            }
            if (input.equals("3")) {
                studentDAO.displayAbsences(login, password);
            }
            if (input.equals("4")) {
                System.out.println("jakie nowe hasło????");
                Scanner sr = new Scanner(System.in);
                userDAO.changePassword(sr.next(), login, password);
            }
            input = scanner.next();
        }
        System.exit(0);
    }
}


