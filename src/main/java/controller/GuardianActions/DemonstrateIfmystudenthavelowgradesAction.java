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

public class DemonstrateIfmystudenthavelowgradesAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select avg(grade) as avgGrades, student_id,  (select subject_name from subjects where " +
                "subjects.subject_id = (select subject_id from lessons where lesson_id = grades.lesson_id)) as " +
                "subjectname from grades WHERE student_id in (select student_id from student where guardian_Id = " + guardian.guardianId +
                " " +
                ") GROUP BY student_id, subjectname ORDER BY student_id DESC;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            if(resultSet.getDouble("avgGrades") < 1.6 && resultSet.getDouble("avgGrades") >= 1){
                Student student = studentDAO.get(resultSet.getInt("student_id"));
                User user = userDAO.get(student.userId);
                System.out.println(user.name + " " + user.surname + "'s grades in " + resultSet.getString(
                        "subjectname") + " are too low and average grade from this subject is " + resultSet.getDouble("avgGrades") + ".");
            }
        }

    }

    @Override
    public String getlabel() {
        return "Display if any of my students have too low grades.";
    }
}
