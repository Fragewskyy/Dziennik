package view;

import java.util.Scanner;

public class MainView {

    public static void sampletext(){
        System.out.println("welcome to my School Diary");
        System.out.println("Please write your login and password");
    }
    public static String readlogin(){
        System.out.println("Login:");
        Scanner  scanner = new Scanner(System.in);

        return scanner.next();
    }
    public static String readPassword(){
        System.out.println("Password:");
        Scanner  scanner = new Scanner(System.in);

        return scanner.next();
    }
}
