package controller.UserActions;

import controller.Action;
import controller.SQLController;
import repository.UserDAO;
import view.MainView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SendTextMessegeAction implements Action {

    @Override
    public void executeQuery() {
        UserDAO userDAO=new UserDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("text for who(login");
        int reciverid = 0;



        try {
            reciverid=userDAO.getId(scanner.next());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Scanner scc=new Scanner(System.in);
        System.out.println("Your messege subject:");
        String subject=scc.nextLine();
        System.out.println("your text mesege:");

        Scanner sc=new Scanner(System.in);
        String messeges=sc.nextLine();





        String userId=null;

        String date= userDAO.getcurrenttime();
        String query="Insert Into dziennik.messages(message_subject,message_text,date,user_id) values('"+subject+"','"+messeges+"','"+date+"','"+reciverid+"');";
        try {
            userId = String.valueOf(userDAO.getId(MainView.getLogin()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("you sendet a messege , nice bro");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getlabel() {

        return "Send text messege";
    }
}
