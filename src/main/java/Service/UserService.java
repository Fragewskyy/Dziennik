package Service;

import DAO.MessegeDAO;
import roleInterfaces.UserInterface;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserService implements UserInterface {
    public void sendTextMessage() throws SQLException {


        String query= MessegeDAO.createmessege();
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Message sent");

    }

    public void changePassword(String newpassword,String login,String password) throws SQLException {
        String query="update dziennik.users  set password='"+newpassword+"' where login='"+login+"' AND password='"+password+"';";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("ustawiłeś nowe hasło:"+newpassword);


    }

    public void logOut() {
        System.out.println("elo");
        System.exit(0);
    }
}
