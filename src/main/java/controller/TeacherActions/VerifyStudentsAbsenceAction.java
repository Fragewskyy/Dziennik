package controller.TeacherActions;

import controller.Action;
import controller.SQLController;

import java.sql.Connection;
import java.sql.SQLException;

public class VerifyStudentsAbsenceAction implements Action {
    @Override
    public void executeQuery() {
        String showallabsencesquery="";
        try {
            Connection connection= SQLController.Connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getlabel() {

        return "Verify student absence";
    }
}
