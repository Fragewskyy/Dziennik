package controller.GuardianActions;

import controller.Action;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Guardian;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;
import s.MainView;

import java.sql.*;
import java.util.ArrayList;

public class ExhibitIfmyStudentsHaveLowAbsenceAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select student_id from student where guardian_id = " + guardian.guardianId;
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
            ResultSet resultSet1 = statement.executeQuery(query1);
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
            if(Math.round((isOnLessonTrue/iter)*100) < 80) {
                System.out.println("Student " + user.name + " " + user.surname + " has too low frequency. Frequency: " + Math.round(isOnLessonTrue/iter * 100) + "%");


            } else {
                System.out.println("Student " + user.name + " " + user.surname + " has correct frequency. Frequency: " + Math.round(isOnLessonTrue/iter * 100) + "%");
            }
        }
    }

    @Override
    public String getlabel() {
        return "Display if my students have too low frequency.";
    }
}
