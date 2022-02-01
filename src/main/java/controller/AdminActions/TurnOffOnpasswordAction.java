package controller.AdminActions;

import controller.Action;
import controller.SQLController;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurnOffOnpasswordAction implements Action {
    public static boolean passChecking;

    @Override
    public void executeQuery() throws SQLException {

        SQLController.Connect();
        Statement statement = SQLController.Connect().createStatement();
        Scanner scanner = new Scanner(System.in);
        int decision;
        getPasswordCheckingValue();
        if(passChecking) {
            System.out.println("Password checking is now enabled.");
        } else {
            System.out.println("Password checking is now disabled.");
        }
        Task:
        while (true) {

            System.out.print("Press <1> to enable password checking, otherwise click <0>: ");
            try {
                decision = scanner.nextInt();
                if (decision == 1 || decision == 0) {
                    break;
                }
                System.out.println("Incorrect choice. Try again!");
            } catch (InputMismatchException e) {
                System.out.println("Incorrect choice. Try again!");
                scanner.next();
            }

        }
        if (decision == 1) {
            String q = "Update admin SET password_check = 1;";
            statement.executeUpdate(q);
            System.out.println("Password checking is now enabled.");

            passChecking = true;
        }
        else if (decision == 0) {
            String q1 = "Update admin SET password_check = 0;";
            statement.executeUpdate(q1);
            System.out.println("Password checking is now disabled.");
            passChecking = false;
        }
        else {
            System.out.println("Wrong number");
        }
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=."
                + "*[A-Z])(?=.*\\d)"
                + "(?=.*[-+_!@#$%^&*., ?]).+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if(passChecking) {
            return m.matches() && password.length() >= 6;
        } else {
            return true;
        }

    }

    public static void getPasswordCheckingValue() throws SQLException {
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        String query = "SELECT password_check FROM admin where admin_id=1;";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        if(resultSet.getInt("password_check") == 1) {
            passChecking = true;
        } else {
            passChecking = false;
        }
    }

    @Override
    public String getlabel() {
        return "Turn on/off checking password format during changing it.";
    }
}
//Login:
//        cverden13@nbcnews.com
//Password:
//        9mA2g7N