package menues;

import java.util.Scanner;

public class ViewforTeacher {
    public static void samplemessege(){
        System.out.println("Clik to :");
        System.out.println("x to logout");
        System.out.println("1 to add new student");
        System.out.println("2 to create new guardian");
        System.out.println("3 to add student to a class");
        System.out.println("4 to modify a grade of a student");
        System.out.println("5 to generate a certificat");
        System.out.println("6 to generate multiple certificats of your choice( write a list of students separeted by comms)");
        System.out.println("7 to add a student do a guardian");
        System.out.println("8 to verify an absence");
        System.out.println("9 to change password");
        System.out.println("10 to  send messege");
    }


    public void view(){
        samplemessege();
        Scanner scanner=new Scanner(System.in);
        String input=scanner.next();
        while (input!="x") {

            if (input.equals("1")) {

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
            if (input.equals("9")) {
                ///daosudent showabsence
            }
            if (input.equals("10")) {
                ///daosudent showabsence
            }
        }
    }


}
