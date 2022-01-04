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

public class GenerateReportsForStudentswhoFailAction implements Action {
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
                ") GROUP BY student_id, lesson_id ORDER BY student_id DESC;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            if(resultSet.getDouble("avgGrades") < 1.6){
                Student student = studentDAO.get(resultSet.getInt("student_id"));
                User user = userDAO.get(student.userId);
                System.out.println("!ATTENTION! Student " + user.name + " " + user.surname + " is going to fail " +
                        "promotion from " + resultSet.getString(
                        "subjectname") + ". Average equals " + resultSet.getDouble("avgGrades"));
            }
        }

    }

    @Override
    public String getlabel() {
        return "Generate reports for students who are going to fail promotion";
    }
}
