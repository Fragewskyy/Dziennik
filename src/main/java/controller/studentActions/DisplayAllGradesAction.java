package controller.studentActions;

import controller.Action;
import controller.SQLController;
import repository.UserDAO;
import view.MainView;

import java.sql.*;

public class DisplayAllGradesAction implements Action {
    @Override
    public void executeQuery() {
        UserDAO userDAO=new UserDAO();
        String studentid="null";
        try {
            studentid=String.valueOf(userDAO.getId(MainView.getLogin()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query="SELECT subject_name ,grade FROM dziennik.grades, dziennik.subjects where dziennik.grades.subject_id=dziennik.subjects.subject_id and\n" +
                "student_id="+studentid+" order by subject_name ; ";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            String oldsubject="";
            while(resultSet.next()){
                String subject=resultSet.getString("subject_name");
                String grade=resultSet.getString("grade");
                if(subject.equals(oldsubject)){
                    System.out.print(","+grade);

                }
                else{
                    System.out.println();
                    System.out.print(subject+":"+grade);



                }

                oldsubject=subject;

            }
            System.out.println();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getlabel() {
        return "Display all grades";
    }
}
