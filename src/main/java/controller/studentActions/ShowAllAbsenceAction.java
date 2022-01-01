package controller.studentActions;

import controller.Action;
import controller.SQLController;
import repository.StudentDAO;
import view.MainView;

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
                "(SELECT dziennik.lessons.date FROM dziennik.lessons where dziennik.absences.lesson_id=dziennik.lessons.lesson_id) AS date,is_on_lesson \n" +
                "from dziennik.absences where student_id="+studentId+" order by subject;";
        try {
            Connection connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("subject")+":" );
                System.out.print(rs.getString("date") );
                if(rs.getInt("is_on_lesson")==1) {
                    System.out.println(" -current-");
                }
                else if(rs.getInt("is_on_lesson")==0){
                    System.out.println(" -not current-");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getlabel() {
        return "show all absence ";
    }
}
