package controller.studentActions;

import controller.Action;
import controller.SQLController;
import repository.StudentDAO;
import s.MainView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowAllAbsenceAction implements Action {
    @Override
    public void executeQuery() {
        StudentDAO studentDAO=new StudentDAO();
        String studentId=String.valueOf(studentDAO.getstudentid(MainView.getLogin()));
        String query ="SELECT (SELECT subject_name FROM dziennik.subjects where subject_id=\n" +
                "(SELECT subject_id FROM dziennik.lessons where dziennik.absences.lesson_id=dziennik.lessons.lesson_id)) as subject,\n" +
                "(SELECT dziennik.lessons.lesson_date FROM dziennik.lessons where dziennik.absences" +
                ".lesson_id=dziennik.lessons.lesson_id) AS date,is_on_lesson " +
                "from dziennik.absences where student_id="+studentId+" and is_on_lesson = 0 order by subject;";
        try {
            Connection connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getDate("date") + " | " + rs.getString("subject"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getlabel() {
        return "Display all my absences.";
    }
}
