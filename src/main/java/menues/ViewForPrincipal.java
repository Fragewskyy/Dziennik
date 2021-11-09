package menues;

import java.util.Scanner;

public class ViewForPrincipal {
    public void view(){
        System.out.println("Clik to :");
        System.out.println("x to logout");
        System.out.println("1 to  send messege");
        System.out.println("3 to change password");
        System.out.println("4 to create a new teacher");
        System.out.println("5 to display all persons");
        System.out.println("6 to create new class");
        System.out.println("7 to set limit of lessons");
        System.out.println("8 to display a list of teachers");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.next();
        if (input.equals("x")) {
            System.out.println("elo");
            System.exit(0);
        }
        if (input.equals("1")) {
            ///daoteacher add student
        }
        if (input.equals("2")) {
            ///daoteacher
        }
        if (input.equals("3")) {
            ///daosudent showabsence
        }
        if (input.equals("4")) {
            ///daosudent showabsence
        }
        if (input.equals("5")) {
            ///daosudent showabsence
        }
        if (input.equals("6")) {
            ///daosudent showabsence
        }
        if (input.equals("7")) {
            ///daosudent showabsence
        }
        if (input.equals("8")) {
            ///daosudent showabsence
        }
    }
}
