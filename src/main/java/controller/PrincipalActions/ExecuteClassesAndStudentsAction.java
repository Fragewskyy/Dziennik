package controller.PrincipalActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Student;
import model.peoplesRoles.Teacher;
import repository.StudentDAO;
import repository.TeacherDAO;
import repository.UserDAO;

import java.sql.*;

public class ExecuteClassesAndStudentsAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        StudentDAO studentDAO = new StudentDAO();
        UserDAO userDAO = new UserDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        int iter = 0;
        for (Student student : studentDAO.getAll()) {
            iter += 1;
            String query = "SELECT class_name FROM classes WHERE class_id = " + student.classId + ";";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            try {
                System.out.println(iter + ". Student - Name: " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Current class: " + resultSet.getString("class_name"));
            } catch (SQLException e) {
                System.out.println(iter + ". Student - Name: " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Not assigned to any class");
            }
        }
        for (Teacher teacher : teacherDAO.getAll()) {
            iter += 1;
            String query = "SELECT class_name FROM classes WHERE teacher_Id = " + teacher.teacherId + ";";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println(iter + ". Teacher - " + "Name: " + userDAO.get(teacher.userId).name + " " + userDAO.get(teacher.userId).surname + " | Main tutor of class: " + resultSet.getString("class_name"));
            } else {
                System.out.println(iter + ". Teacher - " + "Name: " + userDAO.get(teacher.userId).name + " " + userDAO.get(teacher.userId).surname);
            }
        }


    }

    @Override
    public String getlabel() {
        return "Display all classes + students.";
    }
}
