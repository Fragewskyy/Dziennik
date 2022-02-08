package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import repository.UserDAO;
import s.MainView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class VerifyStudentsAbsenceAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//
//        UserDAO userDAO = new UserDAO();
//        int userId = userDAO.getId(MainView.getLogin());
//        Connection connection = SQLController.Connect();
//        Statement statement = SQLController.Connect().createStatement();
//        String query = "SELECT * FROM messages WHERE message_subject = 'Excuse.' and recipient_id = " + userId + ";";
//
//        ResultSet resultSet = statement.executeQuery(query);
//        int iter = 0;
//        while (resultSet.next()) {
//            System.out.println("Excuse nr " + iter);
//            System.out.println(resultSet.getString("message_text"));
//            System.out.print("If you want excuse it press 'y' if no press 'n': ");
//            while (true) {
//                String choice = scanner.next();
//                if (Objects.equals(choice, "n") || Objects.equals(choice, "y")) {
//                    if (choice == "y") {
//                        String modifiedAbsence = resultSet.getString("message_text").replace("Hello, please excuse me" +
//                                " for the following absences: ", "");
//                        modifiedAbsence = modifiedAbsence.replace(" Student: ", "");
//                        modifiedAbsence = modifiedAbsence.replace(" | Subject: ", ",");
//                        modifiedAbsence = modifiedAbsence.replace("  | Date: ", ",");
//                        List<String> absencesToExcuse = Arrays.asList(modifiedAbsence.split(","));
//
//                        for(int i = 0; i < (absencesToExcuse.size()/3); i++) {
//
//                        }
//
//
//
//                    }
//
//                } else {
//                    System.out.println("Wrong choice (must be 'y' or 'n')!");
//                }
//            }
//        }
//
    }

    @Override
    public String getlabel() {

        return "Verify student absences.";
    }
}
