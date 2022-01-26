package controller.PrincipalActions;

import controller.Action;
import controller.AdminActions.TurnOffOnpasswordAction;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Teacher;
import repository.TeacherDAO;
import repository.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SetUpTeacherAction implements Action {
    @Override
    public void executeQuery() throws SQLException {

        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();

        ArrayList<String> loginList = new ArrayList<>();

        String queryLogins = "select login from users;";
        ResultSet loginResultSet = statement.executeQuery(queryLogins);
        while (loginResultSet.next()){
            loginList.add(loginResultSet.getString("login"));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type name: ");
        String n = scanner.next();
        System.out.print("Type surname: ");
        String s = scanner.next();

        String l;

        while(true){
            System.out.print("Type login: ");
            l = scanner.next();
            if(!(loginList.contains(l))){
                break;
            }
            else {
                System.out.println("This login already existed! Type again.");
            }
        }



        String correctP;
        while (true) {
            System.out.print("Set password: ");
            String p = scanner.next();
            if (TurnOffOnpasswordAction.isValidPassword(p)) {
                correctP = p;
                break;
            } else {
                System.out.println("Password doesn't match criteria. Type once again.");
            }
        }

        User user = new User(1, l, correctP, n, s, 3);
        TeacherDAO teacherDAO = new TeacherDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.save(user);
        String query = "select user_id from users where users.login = '" + l + "';";

        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        teacherDAO.save(new Teacher(1, resultSet.getInt("user_id")));
        System.out.println("Teacher added successfully");


    }

    @Override
    public String getlabel() {
        return "Create new teacher.";
    }
}
