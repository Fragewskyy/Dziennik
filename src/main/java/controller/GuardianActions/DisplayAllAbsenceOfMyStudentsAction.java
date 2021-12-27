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

public class DisplayAllAbsenceOfMyStudentsAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select is_on_lesson, student_id, (select lesson_date from lessons where lessons.lesson_id = " +
                "absences.lesson_id) as date,  (select subject_name from subjects where subjects.subject_id = (select" +
                " subject_id from lessons where lessons.lesson_id = absences.lesson_id)) as subjectname from absences" +
                " WHERE student_id in (select student_id from student where guardian_Id = " + guardian.guardianId + ") GROUP BY" +
                " " +
                "student_id ORDER BY student_id DESC; ";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int iter = 0;
        while (resultSet.next()){
            if(resultSet.getInt("is_on_lesson") == 0){
                iter += 1;
                Student student = studentDAO.get(resultSet.getInt("student_id"));
                User user = userDAO.get(student.userId);
                System.out.println("Student: " + user.name + " " + user.surname + " | Subject: " + resultSet.getString(
                        "subjectname") + " " +
                        " | Date: " + resultSet.getDate("date"));
            }
        }
        if(iter == 0) {
            System.out.println("Your students are not absent.");
        }
    }

    @Override
    public String getlabel() {
        return "Display All Absence Of My Students";
    }
}
