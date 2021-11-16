package daos;

import roleInterfaces.UserInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements UserInterface {
    public void sendTextMessage() {

    }

    public void changePassword(String newpassword,String login,String password) throws SQLException {
        String query="update dziennik.users  set password='"+newpassword+"' where login='"+login+"' AND password='"+password+"';";
        Connection connection= DriverManager.getConnection(SQLmanager.getUrl(), SQLmanager.getName(), SQLmanager.getPassword());
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("ustawiłeś  nowe hasło:"+newpassword);


    }

    public void logOut() {
        System.out.println("elo");
        System.exit(0);
    }
}
