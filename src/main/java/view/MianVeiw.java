package view;

import java.util.Scanner;

public class MianVeiw  {

    public void sampletext() {

        System.out.println("Welcome in out diary nie no nie znam sie na angielskim");


    }


    public String readLogin() {
        System.out.println("Login:");
        Scanner scanner = new Scanner(System.in);
        String login =scanner.nextLine();

        return login;

    }
    public String readPasword(){
        System.out.println("Password:");
        Scanner scanner = new Scanner(System.in);
        String password =scanner.nextLine();

        return password;
    }
}
