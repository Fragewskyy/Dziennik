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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DisplayAllAbsenceOfMyStudentsAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select absence_id, is_on_lesson, student_id, (select lesson_date from lessons where lessons" +
                ".lesson_id = absences.lesson_id) as date,  (select subject_name from subjects where subjects" +
                ".subject_id = (select subject_id from lessons where lessons.lesson_id = absences.lesson_id)) as " +
                "subjectname from absences WHERE student_id in (select student_id from student where guardian_Id = " + guardian.guardianId + ") ORDER BY student_id DESC;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME, SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Set<Integer> studentIds = new HashSet<>();
        while (resultSet.next()) {
            if (resultSet.getInt("is_on_lesson") == 0) {
                studentIds.add(resultSet.getInt("student_id"));
            }
        }
        String[] absenceT = {"", ""};
        resultSet.close();
        ResultSet resultSet1 = statement.executeQuery(query);
        List<Integer> studentIdsList = new ArrayList<>(studentIds);

        while (resultSet1.next()) {
            if (resultSet1.getInt("is_on_lesson") == 0) {
                for (Integer studentId : studentIds) {
                    if(resultSet1.getInt("student_id") == studentId) {
                        absenceT[studentIdsList.indexOf(studentId)] += ("- Subject: " + resultSet1.getString(
                                "subjectname") + " " + " | Date: " + resultSet1.getDate("date") + "\n");
                    }
                }
            }
        }
//        System.out.println(studentIds);
//        System.out.println(absences);
        for (Integer studentId : studentIds) {
            Student student = studentDAO.get(studentId);
            User user = userDAO.get(student.userId);
            System.out.println(user.name + " " + user.surname + "'s absences: ");
            System.out.println(absenceT[studentIdsList.indexOf(studentId)]);

        }
    }

    @Override
    public String getlabel() {
        return "Display absences of my students.";
    }
}
