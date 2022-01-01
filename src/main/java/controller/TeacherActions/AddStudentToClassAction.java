package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.User;
import repository.ClassDAO;
import repository.StudentDAO;
import repository.TeacherDAO;
import repository.UserDAO;
import view.MainView;

import java.sql.*;
import java.util.Scanner;

public class AddStudentToClassAction implements Action {
    @Override
    public void executeQuery() {
        ClassDAO classDAO=new ClassDAO();
        StudentDAO studentdaoo=new StudentDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("what class ???");
        String studentid=studentdaoo.getstudentid(MainView.getLogin());
        String classid=classDAO.getclassbyname(scanner.next());
        String query="Update dziennik.students SET  class_id="+classid+" where student_id="+studentid+";";
        try {
            Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(" new class has been assigned");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public String getlabel() {

        return " add student to class";
    }
}
