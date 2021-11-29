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
    public void displayAbsences() {

    }


}
