package controller.TeacherActions;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.parser.Path;
import com.sun.scenario.effect.ImageData;
import controller.Action;
import controller.GenerateCertificate;
import controller.SQLController;
import repository.ClassDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GenerateCertificateForAllStudentsAction implements Action {
    public static ArrayList<Integer> showstudentsfromclass(String classid){
        java.util.List<Integer> result = new ArrayList<Integer>();
        String query = "SELECT student_id FROM dziennik.student where class_id="+classid+";";
        try {
            Connection connection= SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                result.add(resultSet.getInt("student_id"));
            }
            return (ArrayList<Integer>) result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    @Override
    public void executeQuery() throws SQLException {
        Connection connection= SQLController.Connect();
        Statement statement = connection.createStatement();

        ClassDAO classDAO=new ClassDAO();
        Scanner scanner = new Scanner(System.in);

        int iterator = 0;

        String query1 = "SELECT * FROM classes;";
        ResultSet resultSet1 = statement.executeQuery(query1);
        while (resultSet1.next()){
            iterator += 1;
            System.out.println(resultSet1.getInt("class_id") + ". " + resultSet1.getString("class_name"));
        }

        int choice1;
        while (true) {
            System.out.print("Select the class to which you want to assign the student (type ID): ");
            try {
                choice1 = scanner.nextInt();
                if (choice1 >= 1 && choice1 <= iterator) {
                    break;
                }
                System.out.println("Try again!");
            } catch (InputMismatchException e) {
                System.out.println("Try again!");
                scanner.next();
            }
        }


        ArrayList<Integer> classStudents=showstudentsfromclass(String.valueOf(choice1));

        for(int studentid:classStudents) {

            GenerateCertificate generateCertificate = new GenerateCertificate();
            try {
                generateCertificate.executeQuery(studentid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        System.out.println("Successfully generated!");


    }

    @Override
    public String getlabel() {

        return "Generate certificates for all students in class.";
    }
}
