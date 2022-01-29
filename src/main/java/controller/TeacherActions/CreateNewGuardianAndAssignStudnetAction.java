package controller.TeacherActions;

import controller.Action;
import controller.AdminActions.TurnOffOnpasswordAction;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Guardian;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewGuardianAndAssignStudnetAction implements Action {



    @Override
    public void executeQuery() throws SQLException {

        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();

        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);
        GuardianDAO guardianDAO=new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        System.out.print("Type login of a new guardian: ");
        String newlogin=scanner.next();
        System.out.print("Type password of a new guardian: ");

        String correctP;
        while (true) {
            System.out.print("Set password: ");
            String newpassword=scanner.next();
            if (TurnOffOnpasswordAction.isValidPassword(newpassword)) {
                correctP = newpassword;
                break;
            } else {
                System.out.println("Password doesn't match criteria. Type once again.(At least 6 letters, 1 big char," +
                        " 1 special character and 1 number).");
            }
        }
        String guardianname;
        String guardianSurname;

        ArrayList<String> nameAndSurnames = new ArrayList<>();

        String queryOfGettingNames = "SELECT name, surname FROM users;";
        ResultSet resultSet1 = statement.executeQuery(queryOfGettingNames);
        while (resultSet1.next()){
            nameAndSurnames.add(resultSet1.getString("name") + " " + resultSet1.getString("surname"));
        }

        while (true) {
            System.out.print("Type name of a new guardian: ");
            guardianname = scanner.next();
            System.out.print("Type surname of a new guardian: ");
            guardianSurname = scanner.next();
            if (nameAndSurnames.contains(guardianname + " " + guardianSurname)) {
                System.out.println("Guardian with this name and surname already exists. Try again!");
            } else {
                break;
            }

        }
        int guardianroleid=2;
        User user = new User(newlogin,correctP,guardianname,guardianSurname,guardianroleid);

        System.out.print("Type phone number to new guardian: ");
        String guardianphonenumber=scanner.next();

        userDAO.save(user);

        Guardian guardian = new Guardian(guardianphonenumber, userDAO.getId(newlogin));

        guardianDAO.save(guardian);

        System.out.println("Guardian successfully added, now assign students.");
        System.out.println();




        int iter = 0;
        for (Student student : studentDAO.getAll()) {
            iter += 1;
            String query = "SELECT class_name from classes WHERE class_id = (select class_id from student where " +
                    "student_id = " + student.studentId + ");";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            try {
                System.out.println(iter + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Current class: " + resultSet.getString("class_name"));
            } catch (SQLException e) {
                System.out.println(iter + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Not assigned to any class");
            }
        }
        String[] sList;
        ArrayList<Integer> iList = new ArrayList<>();
        Task:
        while (true) {
            System.out.print("Select students to which you want to assign guardian (for example: 1,2,3,4,5): ");
            String s = scanner.next();
            sList = s.split(",");
            for (String s1 : sList) {
                try {
                    iList.add(Integer.parseInt(s1));
                } catch (NumberFormatException e) {
                    System.out.println("Try again!");
                    continue Task;
                }

            }
            break;
        }
        for (String s1 : sList) {
            String assignGuardianQuery =
                    "UPDATE `dziennik`.`student` SET `guardian_id` = '" + guardianDAO.get(userDAO.getId(newlogin)).guardianId +
                    "' " +
                    "WHERE " +
                    "(`student_id` = " +
                    "'" + Integer.parseInt(s1) + "');";
            statement.executeUpdate(assignGuardianQuery);

        }

        System.out.println("Students succesfully assigned to a new guardian.");


    }

    @Override
    public String getlabel() {

        return "Create new guardian and assign student.";
    }
}
