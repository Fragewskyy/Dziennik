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
import java.util.Objects;
import java.util.Scanner;

public class SendExcuseToTeacherAction implements Action {
    @Override
    public void executeQuery() throws SQLException {

        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));

        String queryOfGettingStudentIds = "select student_id from student where guardian_id = " + guardian.guardianId + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();

        ResultSet resultSet1 = statement.executeQuery(queryOfGettingStudentIds);

        ArrayList<Integer> absenceIds = new ArrayList<>();
        ArrayList<Integer> studentIds = new ArrayList<>();
        ArrayList<String> absences = new ArrayList<>();

        while (resultSet1.next()){
            studentIds.add(resultSet1.getInt("student_id"));
        }
        resultSet1.close();



        for (Integer studentId : studentIds) {
            String query = "select absence_id, is_on_lesson, student_id, (select lesson_date from lessons where lessons" +
                    ".lesson_id = absences.lesson_id) as date,  (select subject_name from subjects where subjects" +
                    ".subject_id = (select subject_id from lessons where lessons.lesson_id = absences.lesson_id)) as " +
                    "subjectname from absences WHERE student_id in (select student_id from student where guardian_Id = " + guardian.guardianId + ") ORDER BY student_id DESC;";
            ResultSet resultSet = statement.executeQuery(query);

            int iter = 0;
            while (resultSet.next()){
                if (resultSet.getInt("is_on_lesson") == 0 && resultSet.getInt("student_id") == studentId) {
                    iter += 1;
                    Student student = studentDAO.get(studentId);
                    User user = userDAO.get(student.userId);
                    System.out.println(iter + ". Student: " + user.name + " " + user.surname + " | Subject: " + resultSet.getString(
                            "subjectname") + " " +
                            " | Date: " + resultSet.getDate("date"));
                    absences.add(iter - 1,
                            (iter + ". Student: " + user.name + " " + user.surname + " | Subject: " + resultSet.getString(
                                    "subjectname") + " " +
                                    " | Date: " + resultSet.getDate("date"))+"\n");
                }
            }
            if (iter == 0){
                System.out.println("Your student aren't absent.");
                continue;
            }
            System.out.print("Type IDs of absences, which you want excuse divided by space, for example (1 2 3 4 5), " +
                    "to excuse all type 'all'" +
                    ": ");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String chosenAbsences = "";
            if (Objects.equals(s, "all")){
                for (String absence : absences) {
                    chosenAbsences += absence;
                }
            } else {
                String[] sModified = s.split(" ");


                int iter1 = 0;

                for (String s1 : sModified) {
                    iter1 += 1;
                    Student student = studentDAO.get(studentId);
                    User user = userDAO.get(student.userId);
                    chosenAbsences += absences.get(Integer.parseInt(s1) - 1);
                }
            }




            int userId = userDAO.getId(MainView.getLogin());



            String message = "Hello, please excuse me for the following absences: \n" +chosenAbsences;
            String subject = "Excuse.";

            String queryOfFindingTeacher = "select teacher_id from classes where classes.class_id = (select class_id " +
                    "from student where student_id = 4)";
            ResultSet resultSet2 = statement.executeQuery(queryOfFindingTeacher);

            resultSet2.next();

            int recipientId = resultSet2.getInt("teacher_id");

            String date = userDAO.getcurrenttime();

            String queryOfSending =
                    "Insert Into dziennik.messages(message_subject,message_text,date,user_id,recipient_id) values('" + subject +
                            "','" + message + "','" + date + "'," + userId + "," + recipientId + ");";

            statement.executeUpdate(queryOfSending);
            System.out.println("DONE!");

        }
        


    }

    @Override
    public String getlabel() {
        return "Send an excuse to teacher.";
    }
}
