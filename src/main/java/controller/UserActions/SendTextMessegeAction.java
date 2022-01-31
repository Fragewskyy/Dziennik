package controller.UserActions;

import controller.Action;
import controller.SQLController;
import repository.UserDAO;
import s.MainView;

import java.sql.*;
import java.util.Scanner;

public class SendTextMessegeAction implements Action {

    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO=new UserDAO();
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();



        int recipientId = 0;
        String nameAndSurname = "";

        while(true){
            System.out.print("Type name and surname of recipient: ");
            nameAndSurname = scanner.nextLine();

            String queryOfGettingIdByName = "Select user_id, name, surname from users;";
            ResultSet resultSet = statement.executeQuery(queryOfGettingIdByName);

            while (resultSet.next()){
                if((resultSet.getString("name") + " " + resultSet.getString("surname")).equals(nameAndSurname)) {
                    recipientId = resultSet.getInt("user_id");
                    break;
                }
            }
            if (recipientId == 0) {
                System.out.println("There is no user in database with this name or surname. Try again.");
            } else {
                break;
            }
        }




        System.out.print("Type subject of your message: ");
        String subject = scanner.nextLine();

        System.out.print("Type content of your message:");
        String message = scanner.nextLine();





        int userId = userDAO.getId(MainView.getLogin());

        String date = userDAO.getcurrenttime();
        String query=
                "Insert Into dziennik.messages(message_subject,message_text,date,user_id,recipient_id) values('" + subject +
                        "','" + message + "','" + date + "'," + userId + "," + recipientId + ");";

        try {
            statement.executeUpdate(query);
            System.out.println("Message successfully sent.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getlabel() {

        return "Send text message.";
    }
}
