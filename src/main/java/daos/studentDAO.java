package daos;

import roleInterfaces.StudentInterface;
import roleInterfaces.UserInterface;

import java.sql.*;

public class studentDAO implements StudentInterface, UserInterface {
    public void displayAllGrades() {

    }

    public void displayAbsences() {

    }

    public void sendTextMessage() {

    }

    public void changePassword(String password) throws SQLException {
        String query="update dziennik.users users set password='"+password+"' where user_id=123;";
        Connection connection= DriverManager.getConnection(SQLmanager.getUrl(), SQLmanager.getName(), SQLmanager.getPassword());
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("masz nowe hasło");


    }

    public void logOut() {
        System.exit(0);
    }
}
