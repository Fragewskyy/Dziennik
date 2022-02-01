package Errors;

import controller.SQLController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SamePersonChecker {
    public static List<String> getInformations(int userid){
        String findinformationsQuery="SELECT login,password,name,surname FROM dziennik.users where user_id="+userid+";";
        try {
            Connection connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findinformationsQuery);
            resultSet.next();
            List<String>result = new ArrayList<>();
            result.add(resultSet.getString("login"));
            result.add(resultSet.getString("password"));
            result.add(resultSet.getString("name"));
            result.add(resultSet.getString("surname"));
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Sorry but we found no students :(");
        System.exit(-1);
        return null;

    }
}
