package roleInterfaces;

import java.sql.SQLException;

public interface StudentInterface {
    public void displayAllGrades(String login, String password) throws SQLException;
    public void displayAbsences();
}
