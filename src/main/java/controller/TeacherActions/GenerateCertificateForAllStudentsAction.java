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
import java.util.Scanner;

public class GenerateCertificateForAllStudentsAction implements Action {
    public static ArrayList<Integer> showstudentsfromclass(String classid){
        java.util.List<Integer> result = new ArrayList<Integer>();
        String query = "SELECT student_id FROM dziennik.students where class_id="+classid+";";
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
    public void executeQuery() {
        ClassDAO classDAO=new ClassDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println(" write your class name");
        String classname=scanner.next();
        String classid=classDAO.getclassbyname(classname);
        ArrayList<Integer> classStudents=showstudentsfromclass(classid);

        for(int studentid:classStudents) {

            GenerateCertificate generateCertificate = new GenerateCertificate();
            try {
                generateCertificate.executeQuery(studentid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @Override
    public String getlabel() {

        return "generate cartificate for all student";
    }
}
