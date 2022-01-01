package controller.GuardianActions;

import controller.Action;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Guardian;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;
import view.MainView;

import java.sql.*;

public class ExecuteAllmarksAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select grade, student_id,  (select subject_name from subjects where " +
                "subjects.subject_id = grades.subject_id) as subjectname from grades WHERE student_id in (select " +
                "student_id from " +
                "student where guardian_Id = " + guardian.guardianId +") GROUP BY student_id, subject_id ORDER BY " +
                "student_id" +
                " DESC;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Student student = studentDAO.get(resultSet.getInt("student_id"));
            User user = userDAO.get(student.userId);
            System.out.println("Student: " + user.name + " " + user.surname + " Subject: " + resultSet.getString(
                    "subjectname") + " " + "Grade: " + resultSet.getInt("grade"));

        }

    }

    @Override
    public String getlabel() {
        return "Execute All marks";
    }
}
