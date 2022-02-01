package controller.TeacherActions;

import controller.Action;
import controller.AdminActions.TurnOffOnpasswordAction;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Student;
import repository.ClassDAO;
import repository.StudentDAO;
import repository.TeacherDAO;
import repository.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MakeNewStudentAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        SQLController.Connect();
        Statement statement = SQLController.Connect().createStatement();

        ArrayList<String> loginArray = new ArrayList<>();

        String queryOfGettingLogin = "SELECT login from users;";
        ResultSet loginList = statement.executeQuery(queryOfGettingLogin);

        while (loginList.next()) {
            loginArray.add(loginList.getString("login"));
        }

        UserDAO userDAO=new UserDAO();
        StudentDAO studentDAO=new StudentDAO();
        TeacherDAO teacherDAO=new TeacherDAO();
        Scanner scanner = new Scanner(System.in);

        String studentName;
        String studentSurname;

        ArrayList<String> nameAndSurnames = new ArrayList<>();

        String queryOfGettingNames = "SELECT name, surname FROM users where role_id = 1;";
        ResultSet resultSet1 = statement.executeQuery(queryOfGettingNames);
        while (resultSet1.next()){
            nameAndSurnames.add(resultSet1.getString("name") + " " + resultSet1.getString("surname"));
        }
        while (true) {
            System.out.print("Type name of a new student: ");
            studentName = scanner.next();
            System.out.print("Type surname of a new student: ");
            studentSurname = scanner.next();
            if (nameAndSurnames.contains(studentName + " " + studentSurname)) {
                System.out.println("Guardian with this name and surname already exists. Try again!");
            } else {
                break;
            }

        }

        String newlogin;
        while (true) {
            System.out.print("Type login of a new student: ");
            newlogin = scanner.next();
            if (loginArray.contains(newlogin)) {
                System.out.println("This login already exists in database. Try again.");
            } else {
                break;
            }
        }
        String correctP;
        while (true) {
            System.out.print("Type password of new student: ");
            String password=scanner.next();
            if (TurnOffOnpasswordAction.isValidPassword(password)) {
                correctP = password;
                break;
            } else {
                System.out.println("Password doesn't match criteria. Type once again (At least 6 letters, 1 big char," +
                        " 1 special character and 1 number).");
            }
        }

        User user=new User(1,newlogin,correctP,studentName,studentSurname,1);
        try {
            userDAO.save(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        System.out.print("Type phone number to student: ");
        String phonenumber=scanner.next();
        Student student=null;
        try {
            student=new Student( userDAO.getId(newlogin),null,null,phonenumber);
            studentDAO.save(student);
            System.out.println("Student successfully added.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public String getlabel() {
        return "Create new student.";
    }
}
