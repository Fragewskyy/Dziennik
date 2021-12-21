package controller.AdminActions;

import controller.Action;
import controller.SQLController;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurnOffOnpasswordAction implements Action {
    public static boolean passChecking = true;
    @Override
    public void executeQuery() throws SQLException {
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        String query = "SELECT password_check FROM admin where admin_id=1;";
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.getInt("password_check") == 1) {
            System.out.println("Password checking is now enabled.");
        } else {
            System.out.println("Password checking is now disabled.");
        }
        System.out.print("Press <1> to enable password checking, otherwise click <0>: ");
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        if (decision == 1) {
            System.out.println("Password checking is now enabled.");
            passChecking = false;
        }
        else if (decision == 0) {
            System.out.println("Password checking is now disabled.");
            passChecking = true;
        }
        else {
            System.out.println("Wrong number");
        }
    }

    public boolean isValidPassword(String password) {
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

    @Override
    public String getlabel() {
        return "Turn Off or On password";
    }
}
