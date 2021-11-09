package menues;

import java.util.Scanner;

public class ViewforGuardian {

    public void view(){
        System.out.println("Clik to :");
        System.out.println("x to logout");
        System.out.println("1 to  send messege");
        System.out.println("2 to verify an absence");
        System.out.println("3 to change password");
        System.out.println("4  to display all grades your student");
        System.out.println("5 display all grades of student (write name) ");
        System.out.println("6  display if any of your students has too many absence");
        System.out.println("7 generate report if your student is going to fail :(");
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
