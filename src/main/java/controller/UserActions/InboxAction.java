package controller.UserActions;

import controller.Action;
import controller.SQLController;
import model.User;
import repository.UserDAO;
import s.MainView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InboxAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        SQLController.Connect();
        Statement statement = SQLController.Connect().createStatement();

        String query =
                "SELECT * FROM messages WHERE recipient_id = (select user_id from users where login = '" + MainView.getLogin() +
                "') ORDER BY date DESC";

        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("New messages:");
        int iter = 0;
        ArrayList<Integer> subjects = new ArrayList<>();
        while (resultSet.next()) {
            iter+=1;
            subjects.add(resultSet.getInt("message_id"));
            System.out.println(iter + ". Subject: " + resultSet.getString("message_subject") + " | Date: " + resultSet.getDate("date"));
        }
        resultSet.close();
        Scanner scanner = new Scanner(System.in);

        int chosenId;

        while (true) {
            System.out.print("Type ID of message which you want to expand: ");
            try {
                chosenId = scanner.nextInt();
                if (chosenId >= 1 && chosenId <= iter) {
                    break;
                }
                System.out.println("Try again!");
            } catch (InputMismatchException e) {
                System.out.println("Try again!");
                scanner.next();
            }


        }
        String queryOfExpandingMessage =
                "SELECT * FROM dziennik.messages WHERE message_id = " + subjects.get(chosenId-1) + ";";

        ResultSet resultSet1 = statement.executeQuery(queryOfExpandingMessage);

        resultSet1.next();

        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(resultSet1.getInt("user_id"));

        System.out.println("Subject: " + resultSet1.getString("message_subject"));
        System.out.println();
        System.out.println("From: " + user.name + " " + user.surname);
        System.out.println();
        System.out.println(resultSet1.getString("message_text"));
        System.out.println();
        System.out.println("Written: " + resultSet1.getDate("date"));


    }

    @Override
    public String getlabel() {
        return "Inbox.";
    }
}
