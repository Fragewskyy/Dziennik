package roleInterfaces;

import java.sql.SQLException;

public interface UserInterface {
    public void sendTextMessage();
    public void changePassword(String newpassword,String login,String password) throws SQLException;
    public void logOut();

}
