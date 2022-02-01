package Errors;

import controller.SQLController;
import model.peoplesRoles.Student;
import repository.StudentDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SameStudentExistError extends Exception{

    public static boolean checksamestudent(String login, String password,String name,String surname){
        StudentDAO studentDAO=new StudentDAO();

        try {
            List<Student> students = studentDAO.getAll();
            for (Student student: students){
                List<String> info=SamePersonChecker.getInformations(student.getStudentId());
                String xlogin=info.get(0);
                String xpassword=info.get(1);
                String xname=info.get(2);
                String xsurname=info.get(3);
                if(student.equals(xlogin)  | student.equals(xpassword)| student.equals(xname) | student.equals(xsurname)){
                    return false;


                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
