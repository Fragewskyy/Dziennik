package Service;

import roleInterfaces.UserInterface;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserService implements UserInterface {
    public void sendTextMessage(String login,String password) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Type login of message recipient; ");
        String recipientLogin = scanner.next();
        System.out.println("Type subject of message: ");
        String subject = scanner.next();
        System.out.println("Type message: ");
        String messageText = scanner.next();
        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

        String query="INSERT INTO messages(message_subject, message_text, date, user_id) VALUES ('" + subject + "', '" + messageText + "', '" + modifiedDate + "', (SELECT user_id FROM users WHERE login = '" + recipientLogin + "'));" ;
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
