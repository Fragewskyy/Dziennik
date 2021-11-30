package Service;

import roleInterfaces.UserInterface;

import java.sql.*;
import java.util.Scanner;

public class UserService implements UserInterface {
    public void sendTextMessage(String login,String password) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Do kogo piszemy warjacie??, podaj login");
        String someoneslogin=scanner.next();
        System.out.println("jaki temat");
        String subject=scanner.next();
        System.out.println("treść wiadomości");
        String textmessege=scanner.next();

        String query="INSERT INTO dziennik.messeges(messege_subject,messege_text) " +
                "VALUSE('"+subject+"','"+textmessege+"','"+SQLmanager.findidbylogin(someoneslogin)+");" ;
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("wysłałeś wiadomość");

    }

    public void changePassword(String newpassword,String login,String password) throws SQLException {
        String query="update dziennik.users  set password='"+newpassword+"' where login='"+login+"' AND password='"+password+"';";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(), SQLconector.getPassword());
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("ustawiłeś  nowe hasło:"+newpassword);


    }

    public void logOut() {
        System.out.println("elo");
        System.exit(0);
    }
}
