package s;

import controller.SQLController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MainView {
    static String login;
    static String passwrod;
    public static void sampletext() throws SQLException {

        SQLController.Connect();
        Statement statement = SQLController.Connect().createStatement();

        ArrayList<String> loggingData = new ArrayList<>();


        String query = "SELECT login, password FROM users";
        ResultSet loginAndPasswordResultSet = statement.executeQuery(query);
        while (loginAndPasswordResultSet.next()){
            loggingData.add(loginAndPasswordResultSet.getString("login") + " " + loginAndPasswordResultSet.getString(
                    "password"));
        }

        System.out.println("Welcome to the electronic diary!");
        System.out.println("Please type your login and password.");

        while (true) {
            MainView.readlogin();
            MainView.readPassword();
            if (loggingData.contains(MainView.getLogin() + " " + MainView.getPasswrod())) {
                break;
            } else {
                System.out.println("Incorrect login or password. Try again!");
            }
        }

    }
    public static void readlogin(){
        System.out.print("Login: ");
        Scanner  scanner = new Scanner(System.in);
        MainView.setLogin(scanner.next());

    }
    public static void  readPassword(){
        System.out.print("Password: ");
        Scanner  scanner = new Scanner(System.in);

        MainView.setPasswrod(scanner.next());
    }

    public static String getLogin() {
        return login;
    }

    public static  void setLogin(String logi) {
        login = logi;
    }

    public static String getPasswrod() {
        return passwrod;
    }

    public static  void setPasswrod(String passwro) {
        passwrod = passwro;
    }
}
