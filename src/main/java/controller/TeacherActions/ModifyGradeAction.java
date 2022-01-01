package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Teacher;
import repository.StudentDAO;
import repository.TeacherDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ModifyGradeAction implements Action {
    @Override
    public void executeQuery() {
        TeacherDAO teacherDAO=new TeacherDAO();
        StudentDAO studentDAO=new StudentDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("student login pls");
        String studentid=studentDAO.getstudentid(scanner.nextLine());
        String teachersubject=teacherDAO.getsubject();
        String show="SELECT subject_name ,grade FROM dziennik.grades, dziennik.subjects where dziennik.grades.subject_id=dziennik.subjects.subject_id and"+
                "student_id="+studentid+" and subject_name ='"+teachersubject+"' order by subject_name" ;
        try {
            Connection connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(show);
            System.out.println("Select grade you wan to modify");
            while (resultSet.next()) {


                System.out.print(resultSet.getString("subject_name"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public String getlabel() {
        return "modify grade ";
    }
}
