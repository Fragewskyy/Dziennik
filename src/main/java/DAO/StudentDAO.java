package DAO;

import Service.SQLconector;
import model.User;
import model.peoplesRoles.Student;
import roleInterfaces.StudentInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDAO {
    public void createStudent() throws SQLException {
        User user = new User();
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type login of new student: ");
        user.login = scanner.next();
        System.out.println("Type password for new student: ");
        user.password = scanner.next();
        System.out.println("Type name for new student: ");
        user.name = scanner.next();
        System.out.println("Type surname of new student: ");
        user.surname = scanner.next();
        String query = "INSERT INTO users(login, password, name, surname, role_id) VALUES ('" + user.login + "', '" + user.password + "', '" + user.name + "', '" + user.surname + "', 1)";
        Connection connection= DriverManager.getConnection(SQLconector.getUrl(), SQLconector.getName(),SQLconector.getPassword());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Type class of a new student: ");
        String className = scanner.next();
        student.classId = ClassDAO.getClassIdByName(className);
        System.out.println("Assign supervising teacher to student (type name click 'enter' then type surname): ");







    }

    public void displayAbsences(String login, String password) throws SQLException {

    }
//    craetestudent, findstudentbylogin/password/id/name/surname, showallstudents
}
