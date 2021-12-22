package controller.studentActions;

import controller.Action;
import controller.SQLController;

import java.sql.Connection;
import java.sql.SQLException;

public class ShowAllAbsenceAction implements Action {
    @Override
    public void executeQuery() {
        String query="SELECT * from dziennik.absences where student_id=1;";
        try {
            Connection connection = SQLController.Connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getlabel() {
        return "show all absence ";
    }
}
