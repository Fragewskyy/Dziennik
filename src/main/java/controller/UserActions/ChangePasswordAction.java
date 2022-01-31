package controller.UserActions;

import controller.Action;
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
        System.out.println("What new password do you want??");
        String newpassword = scanner.next();
        String userId= null;
        try {
            userId = String.valueOf(userDAO.getId(MainView.getLogin()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query="update  dziennik.users set users.password='"+newpassword+"' WHERE users.user_id="+userId+"; ";
        try {
            Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public String getlabel() {

        return "Change Password ";
    }
}
