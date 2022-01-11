package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import repository.GuardianDAO;
import repository.StudentDAO;
import view.MainView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertStudentToGuardian implements Action {
    @Override
    public void executeQuery() {
        StudentDAO student=new StudentDAO();
        GuardianDAO guardianDAO=new GuardianDAO();
        System.out.println("what student (write login)??");
        Scanner scanner = new Scanner(System.in);

        String studentid=student.getstudentid(scanner.next());
        String guardianid=guardianDAO.getguardianid(MainView.getLogin());
        String query="Update dziennik.student SET guardian_id="+guardianid+" where student_id="+studentid+";";
        try {
            Connection connection= SQLController.Connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("new guardian has been inserted :)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getlabel() {
        return "insert Student to Guardian";
    }
}
