package DAO;

import java.util.Scanner;

public class GradesDAO {

    public static void add_Grade_to_student(){
        System.out.println("write student login?(login");
        Scanner scanner=new Scanner(System.in);

        String login=scanner.next();
        System.out.println("write subject you learn");
        String subjectname=scanner.next();
        int subjectid=SubjectDAO.findIdbyName(subjectname);
        System.out.println("what grade??");
        int grade=scanner.nextInt();
        String result="INSERT INTO grades VALUSE(grade,student_id,subject_id";

    }
}
