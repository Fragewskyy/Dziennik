package controller.TeacherActions;

import controller.Action;
import model.User;
import model.peoplesRoles.Student;
import repository.ClassDAO;
import repository.StudentDAO;
import repository.TeacherDAO;
import repository.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class MakeNewStudentAction implements Action {
    @Override
    public void executeQuery() {
        UserDAO userDAO=new UserDAO();
        StudentDAO studentDAO=new StudentDAO();
        TeacherDAO teacherDAO=new TeacherDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("student's name");
        String name=scanner.next();
        System.out.println("student surname");
        String surname=scanner.next();
        System.out.println("student login");
        String login=scanner.next();
        System.out.println("student password");
        String password=scanner.next();
        User user=new User(1,login,password,name,surname,1);
        try {
            userDAO.save(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        System.out.println("phone number");
        String phonenumber=scanner.next();
        Student student=null;
        try {
            student=new Student( userDAO.getId(login),null,null,phonenumber);
            studentDAO.save(student);
            System.out.println("you added student nice bro");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public String getlabel() {
        return "Make new Student";
    }
}
