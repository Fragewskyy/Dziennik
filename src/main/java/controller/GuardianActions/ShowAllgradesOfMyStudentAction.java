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
// TODO: 11.01.2022
//sprawdzić to na swojej bazie

public class ShowAllgradesOfMyStudentAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        String query  = "SELECT grade, student_id, (select lesson_date from lessons where lessons.lesson_id = grades" +
                ".lesson_id) as lessonD, (select subject_name from subjects where subjects.subject_id = (select " +
                "subject_id from " +
                "lessons where lessons.lesson_id = grades.lesson_id)) as subjectN from grades where grades.student_id" +
                " in " +
                "(select student_id from student where student.guardian_id = " + guardian.guardianId + ") ORDER " +
                "BY subjectN DESC;";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            Student student = studentDAO.get(resultSet.getInt("student_id"));
            User user = userDAO.get(student.userId);
            System.out.println("Student: " + user.name + " " + user.surname + " | Grade: " + resultSet.getInt("grade") + " | Subject: " + resultSet.getString("subjectN") + " | Date: " + resultSet.getDate("lessonD"));
        }

    }

    @Override
    public String  getlabel() {
        return "Show all grades of my students.";
    }
}
