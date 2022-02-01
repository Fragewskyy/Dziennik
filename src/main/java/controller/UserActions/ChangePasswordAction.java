package controller.UserActions;

import controller.Action;
import controller.AdminActions.TurnOffOnpasswordAction;
import controller.SQLController;
import repository.UserDAO;
import s.MainView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ChangePasswordAction implements Action {
    @Override
    public void executeQuery() {
        UserDAO userDAO=new UserDAO();
        Scanner scanner = new Scanner(System.in);
        String correctP;
        while (true) {
            System.out.print("Set your new password: ");
            String p = scanner.next();
            if (TurnOffOnpasswordAction.isValidPassword(p)) {
                correctP = p;
                break;
            } else {
                System.out.println("Password doesn't match criteria. Type once again.");
            }
        }
        String userId= null;
        try {
            userId = String.valueOf(userDAO.getId(MainView.getLogin()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query="update  dziennik.users set users.password='"+correctP+"' WHERE users.user_id="+userId+"; ";
        try {
            Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Password changed successfully!");

    }

    @Override
    public String getlabel() {

        return "Change password.";
    }
}
