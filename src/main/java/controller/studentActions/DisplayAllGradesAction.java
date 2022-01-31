package controller.studentActions;

import controller.Action;
import controller.SQLController;
import s.MainView;

import java.sql.*;

public class DisplayAllGradesAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        String query =
                "Select student_id from student where user_id = (select user_id from users where login = '" + MainView.getLogin() +
        "')";

        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int studentId = resultSet.getInt("student_id");
        resultSet.close();

        String query1 = "SELECT grade, student_id, (select lesson_date from lessons where lessons.lesson_id = grades" +
                ".lesson_id) as lessonD, (select subject_name from subjects where subjects.subject_id = (select " +
                "subject_id from " +
                "lessons where lessons.lesson_id = grades.lesson_id)) as subjectN from grades where grades.student_id" +
                " =" +
                " " + studentId + " ORDER " +
                "BY subjectN DESC;";
        ResultSet resultSet1 = statement.executeQuery(query1);
        while (resultSet1.next()) {
            System.out.println("Subject: " + resultSet1.getString("subjectN") + " | Grade: " + resultSet1.getInt(
                    "grade") + " | Date: " + resultSet1.getDate("lessonD"));
        }

    }

    @Override
    public String getlabel() {
        return "Display all my grades.";
    }
}
