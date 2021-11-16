package menues;

import daos.studentDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewforStudent {

    public void view() throws SQLException {
        studentDAO studentDAO=new studentDAO();
        System.out.println("Clik to :");
        System.out.println("x to logout");
        System.out.println("1to  send messege");
        System.out.println("2 to display your grades");
        System.out.println("3 to display your  absences ");
        System.out.println("4 to change password");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.next();
        if (input.equals("x")) {
            System.out.println("elo");
            System.exit(0);
        }
        if (input.equals("1")) {
            ///
        }
        if (input.equals("2")) {
            ///daosudent showgrades
        }
        if (input.equals("3")) {
            ///daosudent showabsence
        }
        if (input.equals("4")) {
            System.out.println("jakie nowe hasło????");

            studentDAO.changePassword(scanner.next());
        }
    }
    }


