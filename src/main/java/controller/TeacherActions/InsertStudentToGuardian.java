package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import repository.GuardianDAO;
import repository.StudentDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertStudentToGuardian implements Action {
    @Override
    public void executeQuery() {
        GuardianDAO guardiandao=new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        System.out.println("write guardian login ");
        Scanner scanner = new Scanner(System.in);
        String guardianlogin=scanner.next();
        String guardianid=guardiandao.getguardianid(guardianlogin);
        System.out.println("write student login");
        String studentLogin=scanner.next();
        String studentId=studentDAO.getstudentid(studentLogin);
        String query="update dziennik.students set guardian_id="+guardianid+" where student_id="+studentId+";";
        try {
            Connection connection= SQLController.Connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("you assigned student"+studentLogin+" to guardian"+guardianlogin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public String getlabel() {
        return "insert Student to Guardian";
    }
}
