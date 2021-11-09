package view;

import java.util.Scanner;

public class StartMenu {

    public static void  runMenu(){
        System.out.println("Hello !!!!!!");
        System.out.println("Please give:");
        System.out.println("Login:");
        System.out.println("Password:");
        Scanner scanner = new Scanner(System.in);
        String logincheck=scanner.nextLine();
        String passwordcheck=scanner.nextLine();
        //tutaj metoda (czy jest w bazie taki chłop z takim loginem i hasłem
        System.out.printf("witaj %s %s na dzienniku",logincheck,passwordcheck);

    }
}
