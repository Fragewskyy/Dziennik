package menues;

import daos.UserDAO;
import daos.studentDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewforStudent {

    public void view(String login, String password) throws SQLException {
        studentDAO studentDAO=new studentDAO();
        UserDAO userDAO=new UserDAO();
        System.out.println("Clik to :");
        System.out.println("x to logout");
        System.out.println("1to  send messege");
        System.out.println("2 to display your grades");
        System.out.println("3 to display your  absences ");
        System.out.println("4 to change password");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.next();
        if (input.equals("x")) {
            userDAO.logOut();
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
            Scanner sr=new Scanner(System.in);
            userDAO.changePassword(sr.next(),login,password);
        }
    }
    }


