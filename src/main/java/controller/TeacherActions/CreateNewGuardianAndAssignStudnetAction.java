package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Guardian;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateNewGuardianAndAssignStudnetAction implements Action {



    @Override
    public void executeQuery() {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);
        GuardianDAO guardianDAO=new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        System.out.println("write new guardian login");
        String newlogin=scanner.next();
        System.out.println("write new guardian password");
        String newpassword=scanner.next();
        System.out.println("guardian name write ");
        String guardianname=scanner.next();
        System.out.println("write guardian surname");
        String guardianSurname=scanner.next();
        int guardianroleid=2;
        User user=new User(newlogin,newpassword,guardianname,guardianSurname,guardianroleid);
        int userid=0;
        System.out.println("write guardian new phone number");
        String guardianphonenumber=scanner.next();
        Guardian guardian=new Guardian(guardianphonenumber,userid);

        try {
            userDAO.save(user);
            userid=userDAO.getId(newlogin);
            guardianDAO.save(guardian);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("to what student you want to assign guardian(login)");
        String studentlogin=scanner.next();
        String studentid=studentDAO.getstudentid(studentlogin);
        String query="Update dziennik.student SET guardian_id="+userid+" where student_id="+studentid+";";
        try {
            Connection connection= SQLController.Connect();
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("everything done :)");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String getlabel() {

        return "create new guardian and assign student ";
    }
}
