package controller.UserActions;

import controller.Action;
import controller.SQLController;
import repository.UserDAO;
import view.MainView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SendTextMessegeAction implements Action {
    @Override
    public void executeQuery() {
        UserDAO userDAO=new UserDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("text for who(login");
        String reciver= scanner.next();

        System.out.println("your text mesege");
        String mesege=scanner.next();
        String userId=null;
        String query="Insert Into dziennik.messages(message_subject,message_text,date,user_id) values('123','123','17.12.2021',1);";
        try {
            userId = String.valueOf(userDAO.getId(MainView.getLogin()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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

        return "Send text messege";
    }
}
