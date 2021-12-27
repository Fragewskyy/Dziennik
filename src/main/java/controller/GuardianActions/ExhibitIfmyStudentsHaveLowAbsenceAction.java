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

public class ExhibitIfmyStudentsHaveLowAbsenceAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select is_on_lesson, student_id, (select lesson_date from lessons where lessons.lesson_id = " +
                "absences.lesson_id) as date,  (select subject_name from subjects where subjects.subject_id = (select" +
                " subject_id from lessons where lessons.lesson_id = absences.lesson_id)) as subjectname from absences" +
                " WHERE student_id in (select student_id from student where guardian_Id = " + guardian.guardianId + ") GROUP BY" +
                " " +
                "student_id ORDER BY student_id DESC; ";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<Integer> studentIds = new ArrayList<>();
        while (resultSet.next()){
            studentIds.add(resultSet.getInt("student_id"));
        }
        for (Integer studentId : studentIds) {
            String query1 = "select student_id, is_on_lesson from absences where student_id = " + studentId + ";";
            double iter = 0;
            double isOnLessonTrue = 0;
            double isOnLessonFalse = 0;
            ResultSet resultSet1 = statement.executeQuery(query);
            Student student = studentDAO.get(studentId);
            User user = userDAO.get(student.userId);
            while (resultSet1.next()) {
                if (resultSet1.getInt("is_on_lesson") == 0) {
                    isOnLessonFalse += 1;
                    iter += 1;
                } else if (resultSet1.getInt("is_on_lesson") == 1) {
                    isOnLessonTrue += 1;
                    iter += 1;
                }
            }
            if((isOnLessonTrue/iter) < 0.8) {
                System.out.println("Student " + user.name + " " + user.surname + " has too low frequency, excuse it! " + (isOnLessonTrue/iter * 100) + "%");
            } else {
                System.out.println("Student " + user.name + " " + user.surname + " has correct frequency. Frequency " + (isOnLessonTrue/iter * 100) + "%");
            }
        }
    }

    @Override
    public String getlabel() {
        return "ExhibitIfmyStudentsHaveLowAbsence";
    }
}
