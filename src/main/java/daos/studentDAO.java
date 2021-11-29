package daos;

import roleInterfaces.StudentInterface;
import roleInterfaces.UserInterface;

import java.sql.*;

public class studentDAO implements StudentInterface {
    public void displayAllGrades(String login, String password) throws SQLException {

        Connection connection = DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(),
                SQLconector.getPassword());
        String query = "SELECT (SELECT subject_name FROM subjects WHERE grades.subject_id = subjects.subject_id) AS " +
                "Subject_name, grade FROM grades WHERE student_id = (SELECT student_id FROM student WHERE user_id = " +
                "(SELECT user_id FROM users WHERE login = '"+login+"' AND password = '"+password+"'))";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            System.out.println(resultSet.getString("Subject_name") + ": " + resultSet.getInt("grade"));
        }


    }
    public void displayAbsences(String login, String password) throws SQLException{
        Connection connection = DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(),
                SQLconector.getPassword());
        String query = "SELECT (SELECT lesson_date FROM lessons WHERE absences.lesson_id = lessons.lesson_id) AS " +
                "dataAb, (SELECT subject_name FROM subjects WHERE subjects.subject_id = (SELECT subject_id FROM " +
                "lessons WHERE absences.lesson_id = lessons.lesson_id)) AS subjectname FROM absences WHERE student_id" +
                " = (SELECT student_id FROM student WHERE user_id = (SELECT user_id FROM users WHERE login = '" + login + "'  AND password = '" + password + "')) AND absences.is_on_lesson = 0 ORDER BY dataAb DESC;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println("Date: " + resultSet.getString("dataAb") + " | Lesson name: " + resultSet.getString(
                    "subjectname"));
        }


    }


}
