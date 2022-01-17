package view;

import java.util.Scanner;

public class MainView {
    static String login;
    static String passwrod;
    public static void sampletext(){
        System.out.println("welcome to my School Diary");
        System.out.println("Please write your login and password");
        MainView.readlogin();
        MainView.readPassword();
        System.out.println(MainView.getLogin());
    }
    public static void readlogin(){
        System.out.println("Login:");
        Scanner  scanner = new Scanner(System.in);
        MainView.setLogin(scanner.next());

    }
    public static void  readPassword(){
        System.out.println("Password:");
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
