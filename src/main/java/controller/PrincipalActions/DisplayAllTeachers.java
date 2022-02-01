package controller.PrincipalActions;

import controller.Action;
import controller.SQLController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayAllTeachers implements Action {

    @Override
    public void executeQuery() throws SQLException {
        String showallteachersquerry="Select name , surname FROM dziennik.users inner join dziennik.teacher on dziennik.teacher.user_id=dziennik.users.user_id;";
        Connection connection = SQLController.Connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(showallteachersquerry);
        while (resultSet.next()) {
            String teacherline=resultSet.getString("name")+" "+resultSet.getString("surname");
            System.out.println(teacherline);
        }
    }

    @Override
    public String getlabel() {
        return "Display All Teachers ";
    }
}
