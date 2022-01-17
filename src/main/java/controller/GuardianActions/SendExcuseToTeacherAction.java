package controller.GuardianActions;

import controller.Action;
import controller.SQLController;
import controller.UserActions.SendTextMessegeAction;
import model.User;
import model.peoplesRoles.Guardian;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;
import view.MainView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SendExcuseToTeacherAction implements Action {
    @Override
    public void executeQuery() throws SQLException {

        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select absence_id, is_on_lesson, student_id, (select lesson_date from lessons where lessons" +
                ".lesson_id = " +
                "absences.lesson_id) as date,  (select subject_name from subjects where subjects.subject_id = (select" +
                " subject_id from lessons where lessons.lesson_id = absences.lesson_id)) as subjectname from absences" +
                " WHERE student_id in (select student_id from student where guardian_Id = " + guardian.guardianId + ") GROUP BY" +
                " " +
                "student_id ORDER BY student_id DESC; ";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Integer> absenceIds = new ArrayList<>();
        int iter = 0;
        while (resultSet.next()){
            if(resultSet.getInt("is_on_lesson") == 0){
                iter += 1;
                Student student = studentDAO.get(resultSet.getInt("student_id"));
                User user = userDAO.get(student.userId);
                System.out.println(iter + ". Student: " + user.name + " " + user.surname + " | Subject: " + resultSet.getString(
                        "subjectname") + " " +
                        " | Date: " + resultSet.getDate("date"));
                absenceIds.add(iter-1, resultSet.getInt("absence_id"));
            }
        }
        if(iter == 0) {
            System.out.println("Your students are not absent.");
        }
        System.out.print("Type IDs of absences, which you want excuse divided by space, for example (1 2 3 4 5): ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String[] sModified = s.split(" ");

        User user = userDAO.get(guardianDAO.get(guardian.guardianId).userId);

        for (String sM : sModified) {
            String query1 =
                    "UPDATE absences SET is_on_lesson = 1 WHERE absence_id = " + absenceIds.get(Integer.parseInt(sM)-1) +
                    ";";
            statement.executeUpdate(query1);

        }
        System.out.println("DONE!");
        


    }

    @Override
    public String getlabel() {
        return "Send Excuse To Teacher";
    }
}
