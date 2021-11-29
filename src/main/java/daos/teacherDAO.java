package daos;

import roleInterfaces.TeacherInterface;
import roleInterfaces.UserInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class teacherDAO implements TeacherInterface {

    public void createStudent() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("what user(login)");
        String userilogin=scanner.next();
        String userid=SQLmanager.findidbylogin(userilogin);
        System.out.println("what class??(class name pls give me");
        String classname=scanner.next();
        String classid=SQLmanager.find_ClassId_Byname(classname);
        System.out.println("emmm tutor login??i mean teacher");
        String tutorid=scanner.next();
        System.out.println("and last one , phone number bro");
        String phone=scanner.next();
        String query = "INSERT INTO dziennik.students(user_id,class_id,teacher_id,phone_number) VALUSE('"+userid+"','"+classid+"','"+tutorid+"','"+phone+"',);";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(),SQLconector.getPassword());
        Statement statement= connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("no ogułem dodaleś ucznia");

    }

    public void createGuardian() {

    }

    public void addStudentToClass() {

    }

    public void modifyGrade() {

    }

    public void generateCertificate() {

    }

    public void createMultipleCertificate() {

    }

    public void addStudentToGuardian() {

    }

    public void addStudentAbsence() {

    }

    public void verifyAbsenceExcuse() {

    }


}
